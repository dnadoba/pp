package io.dama.par.actors.find;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.BroadcastRoutingLogic;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import io.dama.par.actors.find.Messages.FindMsg;
import io.dama.par.actors.find.Messages.PleaseCleanupAndStop;
import io.dama.par.actors.find.Messages.ResultMsg;

public class MasterActor extends AbstractActor {
	static final private int WORKER_COUNT = 2;
    private int                numOfChild;
    private final List<String> result = new ArrayList<>();
    private final ActorRef     listener;
    private final Router router;
    
    public MasterActor() {
		listener = getContext().getSystem().actorOf(Props.create(ListenerActor.class));
		Props workerProps = Props.create(WorkerActor.class);
    	ActorSystem system = getContext().getSystem();
    	List<Routee> routees = new ArrayList<Routee>();
    	
    	for(int i = 0; i < WORKER_COUNT; i++) {
    		ActorRef actorRef = system.actorOf(workerProps);
    		
    		getContext().watch(actorRef);
    		routees.add(new ActorRefRoutee(actorRef));
    	}
    	
    	new BroadcastRoutingLogic();
    	router = new Router(new RoundRobinRoutingLogic(), routees);
	}
    
    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(FindMsg.class, this::handleFindMsg) //
                .match(ResultMsg.class, this::handleResultMsg) //
                .build();
    }

    private void handleFindMsg(final FindMsg msg) {
    	this.numOfChild = msg.getFilenames().size();
    	
    	msg.getFilenames().forEach((filename) -> {
    		Messages.WorkMsg message = new Messages.WorkMsg(filename, msg.getSearchword());
    		router.route(message, getSelf());
    	});
    }

    private void handleResultMsg(final ResultMsg msg) {
    	getSender().tell(new PleaseCleanupAndStop(), getSelf());
        this.numOfChild--;
        this.result.addAll(msg.getResult());
        if (this.numOfChild == 0) {
            this.listener.tell(new ResultMsg(this.result), getSelf());
            getContext().stop(getSelf());
        }
    }

}

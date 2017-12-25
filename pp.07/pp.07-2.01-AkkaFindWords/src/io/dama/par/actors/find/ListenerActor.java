package io.dama.par.actors.find;

import akka.actor.AbstractActor;

public class ListenerActor extends AbstractActor {

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Messages.ResultMsg.class, (resultMessage) -> {
					resultMessage.getResult().forEach(System.out::println);
					getContext().getSystem().terminate();
				})
				.build();
	}
	
}

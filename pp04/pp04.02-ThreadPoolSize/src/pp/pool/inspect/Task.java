package pp.pool.inspect;

import java.util.concurrent.ExecutorService;

import pp.pool.run.fixed.Runner;

public class Task implements Runnable {

    private static final int NUMBER_OF_TASKS = 1;

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }

    public static void main(final String[] args) {
        final ExecutorService pool = Runner.test(new Task(), Task.NUMBER_OF_TASKS);
    }

}

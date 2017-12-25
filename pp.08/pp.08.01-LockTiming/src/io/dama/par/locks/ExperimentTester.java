package io.dama.par.locks;

import java.util.ArrayList;
import java.util.List;

public class ExperimentTester {
	static private final int THREAD_COUNT = 4;
	private Experiment experiment;
	ExperimentTester(Experiment experiment) {
		this.experiment = experiment;
	}
	
	double run(double readPercentage, int repetitions, int attempts) {
		int reads = (int)(repetitions * readPercentage);
		int writes = repetitions - reads;
		
		int readsPerThread = reads/THREAD_COUNT;
		int writesPerThread = writes/THREAD_COUNT;
		
		long duration = 0;
		
		int writeModulo = repetitions/writesPerThread;
		
		for(int attempt = 0; attempt < attempts; attempt++) {
			List<Thread> threads = new ArrayList<Thread>();
			
			for(int threadId = 0; threadId < THREAD_COUNT; threadId++) {
				Thread thread = new Thread(() -> {
					for(int i = 0; i < repetitions; i++) {
						if((i % writeModulo) == 0) {
							experiment.incCounter();
						} else {
							experiment.getCounter();
						}
					}
				});
				threads.add(thread);
			}
			long start = System.currentTimeMillis();
			threads.forEach((thread) -> thread.start());
			threads.forEach((thread) -> {
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			long end = System.currentTimeMillis();
			long attempDuration = end - start;
			duration += attempDuration;
		}
		
		double averageDuration = (double)duration / (double)attempts;
		int expectedResult = writesPerThread * THREAD_COUNT * attempts;
		
		if (experiment.getCounter() == expectedResult) {
			System.out.print("Successfull ");
		} else {
			System.out.print("!!!ERROR!!! ");
		}
		System.out.print("Repetitions(" + repetitions + ") - ");
		System.out.print((readPercentage * 100) + "% - ");
		System.out.print(experiment.getClass().getSimpleName() + " ");
		System.out.println(averageDuration + "ms");
		return averageDuration;
	}

	public static void main(String[] args) {
		final int FIRST_COUNT = 100_000;
		final int SECOND_COUNT = 10_000_000;
		final double FIRST_READ_PERCENTAGE = 0.9999;
		final double SECOND_READ_PERCENTAGE = 0.5;
		final int ATTEMPTS = 10;
		new ExperimentTester(new ExpUnsynchronized()).run(FIRST_READ_PERCENTAGE, FIRST_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpUnsynchronized()).run(SECOND_READ_PERCENTAGE, FIRST_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpUnsynchronized()).run(FIRST_READ_PERCENTAGE, SECOND_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpUnsynchronized()).run(SECOND_READ_PERCENTAGE, SECOND_COUNT, ATTEMPTS);
		System.out.println();
		new ExperimentTester(new ExpSynchronized()).run(FIRST_READ_PERCENTAGE, FIRST_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpSynchronized()).run(SECOND_READ_PERCENTAGE, FIRST_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpSynchronized()).run(FIRST_READ_PERCENTAGE, SECOND_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpSynchronized()).run(SECOND_READ_PERCENTAGE, SECOND_COUNT, ATTEMPTS);
		System.out.println();
		new ExperimentTester(new ExpSynchronized()).run(FIRST_READ_PERCENTAGE, FIRST_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpSynchronized()).run(SECOND_READ_PERCENTAGE, FIRST_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpSynchronized()).run(FIRST_READ_PERCENTAGE, SECOND_COUNT, ATTEMPTS);
		new ExperimentTester(new ExpSynchronized()).run(SECOND_READ_PERCENTAGE, SECOND_COUNT, ATTEMPTS);
	}

}

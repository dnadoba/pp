package io.dama.par.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class ReduceTask extends RecursiveTask<Integer> {

    private static int ARRAY_LEN = 0;
    private static int SLICE_LEN = 0;

    private final ArrayList<Integer> array;
    private final int                start;
    private final int                end;

    ReduceTask(final ArrayList<Integer> array, final int start, final int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer sum = 0;
        if ((this.end - this.start) <= ReduceTask.SLICE_LEN) {
            for (int i = this.start; i < this.end; i++) {
                sum += this.array.get(i);
            }
        } else {
            final int mid = this.start + ((this.end - this.start) / 2);
            final ReduceTask left = new ReduceTask(this.array, this.start, mid);
            final ReduceTask right = new ReduceTask(this.array, mid, this.end);
            ForkJoinTask.invokeAll(left, right);
            sum = left.join() + right.join();
        }Sa
        return sum;
    }

    public static void main(final String[] args) {
        for (ReduceTask.ARRAY_LEN = 100; ReduceTask.ARRAY_LEN < 10000000; ReduceTask.ARRAY_LEN = ReduceTask.ARRAY_LEN
                * 10) {
            for (ReduceTask.SLICE_LEN = 4; ReduceTask.SLICE_LEN < 128; ReduceTask.SLICE_LEN = ReduceTask.SLICE_LEN
                    * 2) {
                final ArrayList<Integer> array = new ArrayList<>();
                for (int i = 0; i < ReduceTask.ARRAY_LEN; i++) {
                    array.add(i);
                }
                final long now = System.currentTimeMillis();
                final ReduceTask task = new ReduceTask(array, 0, array.size());
                ForkJoinPool.commonPool().invoke(task);
                // seit Java 8 alternativ: task.invoke();
                final int sum = task.join();
                System.out.printf("ARRAY_LEN: %d, SLICE_LEN: %d, Summe: %d, Zeit: %d \n", ReduceTask.ARRAY_LEN,
                        ReduceTask.SLICE_LEN, sum, System.currentTimeMillis() - now);
            }
        }
    }

}

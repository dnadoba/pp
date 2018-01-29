package io.dama.par.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class FilterTask extends RecursiveAction {

    private static final int ARRAY_LEN = 16;
    private static final int SLICE_LEN = 4;
    private static final int MAX       = 10;

    private final ArrayList<Integer> array;
    private final int                start;
    private final int                end;

    FilterTask(final ArrayList<Integer> array, final int start, final int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((this.end - this.start) <= FilterTask.SLICE_LEN) {
            for (int i = this.start; i < this.end; i++) {
                if (this.array.get(i) > FilterTask.MAX) {
                    this.array.set(i, FilterTask.MAX);
                }
            }
        } else {
            final int mid = this.start + ((this.end - this.start) / 2);
            final FilterTask left = new FilterTask(this.array, this.start, mid);
            final FilterTask right = new FilterTask(this.array, mid, this.end);
            ForkJoinTask.invokeAll(left, right);
        }
    }

    public static void main(final String[] args) {
        final ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < FilterTask.ARRAY_LEN; i++) {
            array.add(i);
        }
        final FilterTask task = new FilterTask(array, 0, array.size());
        ForkJoinPool.commonPool().invoke(task);
        // seit Java 8 alternativ: task.invoke();
        task.join();
        for (final Integer i : array) {
            System.out.println(i);
        }
    }
}

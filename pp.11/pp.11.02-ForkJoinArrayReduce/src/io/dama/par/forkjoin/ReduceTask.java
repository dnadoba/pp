package io.dama.par.forkjoin;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ReduceTask {

    private static final int ARRAY_LEN = 16;
    private static final int SLICE_LEN = 4;

    public static void main(final String[] args) {
        final ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < ReduceTask.ARRAY_LEN; i++) {
            array.add(i);
        }
        // initialen ReduceTask erzeugen
        // ReduceTask`` im *Common Thread Pool* starten
        // ReduceTask`` im *Common Thread Pool* starten
        // auf Ende warten und Summe ausgeben
    }
}

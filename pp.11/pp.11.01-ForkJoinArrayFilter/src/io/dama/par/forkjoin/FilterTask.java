package io.dama.par.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class FilterTask extends RecursiveAction {

    private static final int ARRAY_LEN = 16;
    private static final int SLICE_LEN = 4;
    private static final int MAX   = 10;

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
        // Fallunterscheidung einführen, die prüft, ob weiter rekursiv halbiert werden
        // muss oder ob nun der Rekursionsanker erreicht ist

        // Rekursionsanker: über Array-Elemente mit einer foreach-Schleife iterieren und
        // Filter-Aktion anwenden
        // (muss noch implementiert werden)

        // Array in zwei Hälften aufteilen und beide Hälften rekursiv behandeln:
        final int mid = this.start + ((this.end - this.start) / 2);
        final FilterTask left = new FilterTask(this.array, this.start, mid);
        final FilterTask right = new FilterTask(this.array, mid, this.end);
        ForkJoinTask.invokeAll(left, right);
    }

    public static void main(final String[] args) {
        // Array initialisieren
        // initialen FilterTask erzeugen
        // FilterTask`` im *Common Thread Pool* starten
        // auf das Ende warten
        // gefiltertes Array ausgeben
    }
}

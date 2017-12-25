package io.dama.par.cf.api.future;

import java.util.concurrent.ExecutionException;

public class User {
    static final int RUN = 10;

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final ILib lib = new Lib();
        for (int i = 0; i <= User.RUN; i++) {
            System.out.printf("%2d: %d\n", i, lib.calcAsync().get());
        }
    }
}

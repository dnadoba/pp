package io.dama.par.cf.api;

public class User {
    static final int RUN = 10;

    public static void main(final String[] args) {
        final ILib lib = new Lib();
        for (int i = 0; i <= User.RUN; i++) {
            try {
                System.out.printf("%2d: %d\n", i, lib.calcSync());
            } catch (final Exception e) {
                System.out.printf("%2d: Fehler (%s)\n", i, e.getMessage());
            }
        }
    }
}

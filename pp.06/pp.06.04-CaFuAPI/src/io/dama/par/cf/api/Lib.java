package io.dama.par.cf.api;

import java.util.Random;

public class Lib implements ILib {
    Integer lowestThread;

    public String tabs() {
        String s = "";
        for (int i = 0; i < ((int) Thread.currentThread().getId() - this.lowestThread); i++) {
            s += "\t\t";
        }
        return s;
    }

    @Override
    public Integer calcSync() {
        if ((this.lowestThread == null) || ((int) Thread.currentThread().getId() < this.lowestThread)) {
            this.lowestThread = (int) Thread.currentThread().getId();
        }
        System.out.println(tabs() + "START calc()");
        final Random rand = new Random();
        try {
            Thread.sleep(3000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(tabs() + "END calc()");
        }
        return rand.nextInt();
    }
}

package io.dama.par.cf.api.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Lib extends io.dama.par.cf.api.Lib implements ILib {
    static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

    @Override
    public Future<Integer> calcAsync() {
        return Lib.pool.submit(() -> calcSync());
    }

}

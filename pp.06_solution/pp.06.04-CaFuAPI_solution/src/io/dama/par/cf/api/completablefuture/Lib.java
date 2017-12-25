package io.dama.par.cf.api.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Lib extends io.dama.par.cf.api.Lib implements ILib {

    @Override
    public CompletableFuture<Integer> calcAsync() {
        return CompletableFuture.supplyAsync(() -> calcSync());
    }
}

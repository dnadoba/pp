package io.dama.par.cf.api.completablefuture;

import java.util.concurrent.CompletableFuture;

public interface ILib extends io.dama.par.cf.api.ILib {
    public CompletableFuture<Integer> calcAsync() throws Exception;
}

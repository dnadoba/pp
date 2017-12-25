package io.dama.par.cf.api.future;

import java.util.concurrent.Future;

public interface ILib extends io.dama.par.cf.api.ILib {
    public Future<Integer> calcAsync();
}

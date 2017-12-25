# Completable Future #

## asynchrone APIs ##


## Aufgaben ##

* Probieren Sie ``io.dama.par.cf.api.calcSync()`` aus, indem Sie ``User`` laufen lassen.
* Entwerfen Sie ein neues Package ``io.dama.par.cf.api.future`` mit Interface und Implementierung eines zusätzlichen Aufrufs ``calcAsync()``. Dieser Aufruf soll ``calc.Sync`` verpacken und ein ``Future`` zurückgeben, das in einem eigenen Thread-Pool ausgeführt wird:

	Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1)
	
* Entwerfen Sie ein neues Package ``io.dama.par.cf.api.completablefuture`` mit Interface und Implementierung eines zusätzlichen Aufrufs ``calcAsync()``. Dieser Aufruf soll ``calc.Sync`` in einem ``CompletableFuture`` verpacken und im *commonPool* ausführen.
  
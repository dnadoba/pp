# Completable Future #

## RSA-Schlüsselerzeugung ##


## Lösungsskizze ##

Da die ``CompletionStage`` zur Erzeugung von Primzahlen aufwändig ist und parallelisiert ablaufen kann, wurde die Methode ``getAsyncPrim`` hinzugefügt, in der asynchron 4 Versuche unternommen werden, eine Primzahl zu finden. Sobald einer der Versuche erfolgreich ist, wird das Ergebnis mit ``cfPrim.join()`` abgerufen. Der Cast zu ``BigInteger`` ist erforderlich, weil ``anyOf`` und ``allOf`` immer ``CompletableFuture<Object>`` als Resultat haben.  Der ``Supplier`` wird asynchron mit ``supplyAsync`` gestartet. Das Ergebnis ist ein `CompletableFure<BigInteger>`, das nebenläufig realisiert wird. 

Mit ``getAsyncPrim`` werden zwei Primzahlen geholt.

Die beiden Primzahlen werden mit ``thenCombineAsync`` per Multiplikation zusammengeführt zu ``NFuture`` asynchron zusammengeführt.

Genauso wird für Phi verfahren.

Der Rest wird sequentiell berechnet (analog zur Future-Lösung). 
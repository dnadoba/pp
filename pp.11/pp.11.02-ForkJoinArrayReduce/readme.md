# ForkJoin-Framework #

## Reduce als *divide and conquer* ##

Das ForkJoin-Framework eignet sich besonders gut zur Implementierung von Array-Algorithmen.

Alle Werte eines Arrays aufaddieren.

Das Array soll halbiert werden, bis <= 4 Elemente übrig sind.

## Aufgaben ##
* Implementieren Sie die Summierungsfunktion in der Klasse ``ReduceTask``.
* Führen Sie Messungen der Laufzeit durch.
* Visualisieren Sie die Ergebnisse und analysiseren Sie das Ergebnis.

## Anregung (bei Interesse, Zeit und Gelegenheit) ##
* Verallgemeinern Sie ``ReduceTask`` für allgemeine Aggregationsfunktionen und Typen (Typ-Parameter für Klasse und Aggregierungsfunktion über ``FunctionalInterface`` ``BiFunction`` übergeben). Optimieren Sie durch Empirie (finden Sie durch Versuche heraus), welche Array-Größe als Rekursionsanker geeignet ist.

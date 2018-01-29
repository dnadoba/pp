# ForkJoin-Framework #

## in-situ Filterung als *divide and conquer* ##

Das ForkJoin-Framework eignet sich besonders gut zur Implementierung von Array-Algorithmen.

Alle Werte eines Arrays mit ``MAX`` überschreiben, die > ``MAX`` sind.

Das Array soll halbiert werden, bis <= 4 Elemente übrig sind.

## Aufgaben ##
*  Vervollständigen Sie die Methode ``compute``: 
   * führen Sie eine neue Fallunterscheidung ein, die prüft, ob weiter rekursiv halbiert werden muss oder ob nun der Rekursionsanker erreicht ist (das Array soll halbiert werden, bis <= 4 Elemente übrig sind).
   * implementieren Sie die Aktion für den Rekursionsanker: iterieren Sie dazu mit einer for-Schleife über die Array-Elemente und wenden Sie auf jedes Element die Filter-Aktion an (Element mit MAX überschreiben, falls  Element > MAX ist.)
* Schreiben Sie ein Hauptprogramm, mit dem Sie den ``FilterTask`` im *Common Thread Pool* ausprobieren. 
* Wie oft wird bei 16 Elementen geforkt?

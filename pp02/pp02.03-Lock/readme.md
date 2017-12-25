# Konkurrierender Zugriff #

## Gegenseitiger Ausschluss ##

Das _Factory_-Pattern wird durch eine Klasse umgesetzt, die Instanzen (hier der Klasse ``Type``) durch Aufruf einer Methode (statt eines Konstruktors) liefert. Normalerweise handelt es sich um eine Klassenmethode (``static``). In diesem Beispiel realisert die Factory-Methode zusätzlich das _Singleton_-Muster: Die Factory-Methode erzeugt höchstens eine Instanz und speichert sie in einem Klassenattribut (``static``). Ist bereits eine Instanz vorhanden, wird sie von der Factory-Methode zurückgeliefert.


Um das zu verdeutlichen merkt sich ``Type`` wie oft der Konstruktor verwendet wurde (wieviele Instanzen der Klasse es also gibt). In jedem Objekt von ``Type`` ist vermerkt, die wievielte Instanz es gewesen ist. Diese _Seriennummer_ kann durch die Methodee ``getSerial()`` abgefragt werden.

Die Klasse ``Factory`` hat eine ``main``-Methode, die 100 Threads startet. In jedem Thread wird eine Type-Instanz von der Factory-Methode ``getInstance()`` angefordert. Das dauert etwas, da zuerst ``Type.prepare()`` aufegrufen werden muss. In dieser Methode wird asynchron etwas getan, was zeitaufwändig ist. ``Type.prepare()`` muss jedes mal ausgeführt werden, wenn eine Instanz abgerufen werden soll. Es gibt aber keinen inneren Zusammenhang zum Konstruktor von ``Type``. ``Type.prepare()`` ist zudem "Thread-sicher".    



In jedem Thread gibt es in der lokalen Variable ``object`` eine Referenz auf ein ``Type``-Objekt, das über die Factory-Methode ``getInstance()`` abgerufen wurde. Eigentlich sollte es sich immer um dasselbe Objekt handeln. ** Leider stellt die Factory-Methode einen kritischen Abschnitt dar:** In jedem Thread müsste also der Aufruf von ``object.getSerial()`` denselben Wert ``1`` zurückliefern. Kommt es aber zum überlappenden Ausführen von ``getInstance()`` durch zwei Threads, können zwei Instanzen gebildet werden.  

## Aufgaben ##

* Ändern Sie ``Factory`` so, dass der kritische Abschnitt geschützt wird. Das Hauptprogramm muss für das jeweilige ``Type``-``object`` eines jeden Threads dieselbe Seriennummer ``1`` zurückliefern (notwendige Bedingung). 

Die Klasse ``Type`` soll nicht verändert werden. Sie wird hier im JAR ``lib/parSynchType.jar`` bereitgestellt, das im Classpath eingebunden sein sollte (sonst Rechts-Klick auf das JAR und "Build Path/Add To Build Path" wählen.


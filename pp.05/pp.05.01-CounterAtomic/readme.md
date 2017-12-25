# Atomic Variablen #

## Counter threadsicher machen ##

Die Klasse ``Counter`` ist nicht threadsicher.

## Aufgaben ##

* Schreiben Sie eine Main-Methode, die demonstriert, dass ``Counter`` nicht thread-sicher ist. Messen Sie die Ausführungszeit.
* Machen Sie ``Counter`` durch zwei ``synchronized`` threadsicher.
* Wieso müssen nicht alle drei Methoden ``synchronized`` gemacht werden? Was kann man stattdessen tun? 

* Machen Sie ``Counter`` mit einem ``AtomicInteger`` und ``compareAndSet`` threadsicher.
* Benutzen Sie dieselbe Main-Methode, um zu demonstriert, dass ``Counter`` nun thread-sicher ist (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit). Messen Sie die Ausführungszeit.

* Machen Sie ``Counter`` mit einem AtomicInteger und ``in/decrementAndGet`` oder ``getAndDe/Increment`` threadsicher.
* Benutzen Sie dieselbe Main-Methode, um zu demonstriert, dass ``Counter`` nun thread-sicher ist (Achtung: Abwesenheit von Fehlerbeobachtung ist kein Beweis für Korrektheit). Messen Sie die Ausführungszeit.
# Locks #

## Vergleich von ``ReentrantLock``, ``ReentrantReadWriteLock``, ``StampedLock`` und ``synchronized`` ##

## Aufgaben ##
* Implementieren Sie das Interface ``Experiment`` in threadsicherer Art mit ``synchronized``, ``ReentrantLock``, ``ReentrantReadWriteLock`` und ``StampedLock``.
* Fügen Sie jeweils eine ``main``-Methode hinzu, die zwei Threads erzeugt, darin jeweils ``Experiment.experiment()`` ausführt und die Gesamtzeit misst.
* Führen Sie Messungen der Laufzeit durch.
* Vergleichen Sie auch das Zeitverhalten für ``ReentrantLock(false)`` und ``ReentrantLock(true)`` sowie analog bei ``ReentrantReadWriteLock(false)`` und ``ReentrantReadWriteLock(true)``.


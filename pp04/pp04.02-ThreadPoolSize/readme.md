# Parallele Programmierung. Thread Pools #

## Thread Pools ##

Bei diesem Projekt ist das JAR in lib in den CLASSPATH eingebunden (falls nicht: Rechtsklick auf das JAR in lib und "Add to Classpath" wählen). In dem JAR sind zwei Klassen (``pp.pool.run.fixed.Runner`` und ``pp.pool.run.cached.Runner``), die beide jeweils die statische Methode ``test(Runnable r, int tries)`` bereitstellen. In diesen beiden ``Runner.test(...)``-Methoden wird ein Thread Pool angelegt. Das ``Runnable`` ``r`` wird dann ``tries`` mal im Thread Pool ausgeführt.

In ``pp.pool.run.fixed.Runner`` wird dafür ein Thread Pool fester Größe benutzt und in ``pp.pool.run.cached.Runner` ein Cached Thread Pool.

## Aufgaben ##

* Inspizieren Sie den Thread Pool von ``Runner.test()`` indem Sie die ``run()``-Methode mit eigenem Code füllen. Versuchen Sie durch Testläufe zu ergründen, wie groß der Thread Pool jeweils ist bzw. wie er sich verhält. Verwenden Sie dabei statische, innere Klassen oder Lambda-Ausdrücke.
* Fahren Sie am Ende den Threadpool nach einer kurzen Wartezeit durch ein Scheduled Event herunter.


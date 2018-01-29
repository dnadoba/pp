# ForkJoin-Framework #

## in-situ Filterung als *divide and conquer* ##

Das ForkJoin-Framework eignet sich besonders gut zur Implementierung von Array-Algorithmen.

Alle Werte eines Arrays mit ``MAX`` überschreiben, die > ``MAX`` sind.

Das Array soll halbiert werden, bis <= 4 Elemente übrig sind.

## Aufgaben ##

*  Vervollständigen Sie die Methode ``compute``:
 
   * führen Sie eine neue Fallunterscheidung ein, die prüft, ob weiter rekursiv halbiert werden muss oder ob nun der Rekursionsanker erreicht ist (das Array soll halbiert werden, bis <= 4 Elemente übrig sind).
   
	if ((this.end - this.start) <= FilterTask.SLICE_LEN) 
   
   * implementieren Sie die Aktion für den Rekursionsanker: iterieren Sie dazu mit einer for-Schleife über die Array-Elemente und wenden Sie auf jedes Element die Filter-Aktion an (Element mit MAX überschreiben, falls  Element > MAX ist.)
   
	for (int i = this.start; i < this.end; i++) {
		if (this.array.get(i) > FilterTask.MAX) {
        		this.array.set(i, FilterTask.MAX);
		}
	}

Es bietet sich an eine ``for``-Schleife statt eines Iterators zu benutzen, da ein Iterator zu ``ConcurrentModificationException`` führen kann.
             
* Schreiben Sie ein Hauptprogramm, mit dem Sie den ``FilterTask`` im *Common Thread Pool* ausprobieren. 

  1.	Array initialisieren

	final ArrayList<Integer> array = new ArrayList<>();
	for (int i = 0; i < FilterTask.ARRAY_LEN; i++) {
		array.add(i);
	}

  2.	initialen FilterTask erzeugen

    final FilterTask task = new FilterTask(array, 0, array.size());
    
  3.	FilterTask`` im *Common Thread Pool* starten

    ForkJoinPool.commonPool().invoke(task);

   seit Java 8 alternativ: ``task.invoke();``
	
  4.	auf das Ende warten

	task.join();

  5.	gefiltertes Array ausgeben

	for (final Integer i : array) {
    		System.out.println(i);
	}
* Wie oft wird bei 16 Elementen geforkt? 

3 x



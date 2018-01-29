# ForkJoin-Framework #

## Reduce als *divide and conquer* ##

Das ForkJoin-Framework eignet sich besonders gut zur Implementierung von Array-Algorithmen.

Alle Werte eines Arrays aufaddieren.

Das Array soll halbiert werden, bis <= 4 Elemente übrig sind.

## Aufgaben ##
* Implementieren Sie die Summierungsfunktion in der Klasse ``ReduceTask``.

  1. Die Klasse ``ReduceTask`` muss von ``RecursiveTask<Integer>`` erben.

	public class ReduceTask extends RecursiveTask<Integer> { //...
	
  2. Wie bei ``FilterTask`` werden die Instanzvariablen ``array``, ``start`` und ``end`` benutzt:
  
	private final ArrayList<Integer> array;
    private final int                start;
    private final int                end;

    ReduceTask(final ArrayList<Integer> array, final int start, final int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

  3. Wieder muss ``compute()`` überschrieben werden. Nun gibt es aber ``Integer`` als Rückgabewert (Typ-Parameter von Oberklasse):
  
	@Override
    protected Integer compute() { //...
    
  4. Die Elemente werden in der lokalen Variable ``sum`` aufaddiert:
  
	Integer sum = 0;
	if (//...
		//...
		sum += this.array.get(i);
		//...
	} else {
		//...
		sum = left.join() + right.join();
	}
	return sum;
	
  5. Rekursionanker ist wie bei ``FilterTask``: 

	if ((this.end - this.start) <= ReduceTask.SLICE_LEN) { //...
	
  6. und es wird im Anker-Fall wieder mit einer ``for``-Schleife über den Array-Abschnitt iteriert:
  
	for (int i = this.start; i < this.end; i++) { //...
	
  7. Im Rekursionsfall wird das Array wie bei ``FilterTask`` halbiert und rekursiv bearbeitet:
  
	final int mid = this.start + ((this.end - this.start) / 2);
    final ReduceTask left = new ReduceTask(this.array, this.start, mid);
    final ReduceTask right = new ReduceTask(this.array, mid, this.end);
    ForkJoinTask.invokeAll(left, right);  
    
  8. nun gibt es aber bei ``left`` und ``right`` Ergebnisse, auf die gewartet werden muss und die dann miteinander verrechnet werden können (Addition):
  
	sum = left.join() + right.join();
	
  9. Das Hauptprogramm ist ganz analg zu dem von ``FilterTask``, außer dass nun das Ergebnis des "obersten" Task-Aufrufs abgeholt und ausgegeben wird:
  
	final ReduceTask task = new ReduceTask(array, 0, array.size());
    ForkJoinPool.commonPool().invoke(task);
	System.out.printf("Summe: %d\n", task.join()); 
    
* Führen Sie Messungen der Laufzeit durch.
* Visualisieren Sie die Ergebnisse und analysiseren Sie das Ergebnis.

## Anregung (bei Interesse, Zeit und Gelegenheit) ##
* Verallgemeinern Sie ``ReduceTask`` für allgemeine Aggregationsfunktionen und Typen (Typ-Parameter für Klasse und Aggregierungsfunktion über ``FunctionalInterface`` ``BiFunction`` übergeben). Optimieren Sie durch Empirie (finden Sie durch Versuche heraus), welche Array-Größe als Rekursionsanker geeignet ist.

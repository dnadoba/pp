package pp.future;

@FunctionalInterface
public interface Expression<T> {
    public T eval();
}

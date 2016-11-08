import java.util.Collection;

interface MyList<E> extends Collection<E> {
    void printAll();
    boolean addAll(Collection<? extends E> c);
    boolean add(E e);
    boolean remove(Object o);
    E get(int index);
    void clear();
    int size();
    boolean isEmpty();
}

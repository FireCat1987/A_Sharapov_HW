import java.util.Collection;
import java.util.Date;

public interface MyList<E> extends Collection<E> {
    int size();
    boolean isEmpty();
    Object[] toArray();
    boolean add(E e);
    void add(int index, E element);
    boolean remove(Object o);
    boolean remove(int index);
    void clear();
}

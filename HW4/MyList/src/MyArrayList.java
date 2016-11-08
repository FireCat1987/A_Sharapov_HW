import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

class MyArrayList<E> implements MyList<E>{
    private Object[] list;
    private final int DEFAULTSIZE = 1;

    MyArrayList()
    {
        this.list = new Object[DEFAULTSIZE];
    }
    public MyArrayList(Collection<? extends E> c)
    {
        this.list = c.toArray();
    }

    @Override
    public synchronized void printAll() {
        for (Object o : this.list) {
            System.out.println((o == null ? "null" : o.toString()));
        }
    }

    @Override
    public synchronized int size() {
        return this.list.length;
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.list.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object o1 : this.list) {
            if(o1.equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return null;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return null;
    }

    @Override
    public synchronized boolean add(E e) {
        if(this.list[0]==null){
            this.list[0]=e;
            return true;
        }else {
            Object[] newArray = new Object[this.list.length+1];

            System.arraycopy(this.list, 0, newArray, 0, this.list.length);
            newArray[this.list.length] = e;
            this.list=newArray;
            return true;
        }
    }

    @Override
    public synchronized boolean remove(Object o) {
        int l = this.list.length;
        for (int i =0; i<this.list.length-1;i++)
        {
            if (this.list[i].equals(o))
            {
                Object[] newArray = new Object[this.list.length-1];
                System.arraycopy(list, 0, newArray, 0, i);

                System.arraycopy(list, i + 1, newArray, i, newArray.length - i);
                this.list=newArray;
            }

        }
        if(l>this.list.length) return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public E get(int index) {
        return (E) this.list[index];
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public synchronized void clear() {
        this.list = new Object[DEFAULTSIZE];
    }

}

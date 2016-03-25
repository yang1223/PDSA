import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Yang Chi-Chang on 2016/3/25.
 */
public class Deque<E> implements Iterable {

    private Object[] elementData;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public Deque(){
        this.elementData = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void addFirst(E e){
        if(e == null) throw new NullPointerException();
        for(int i = size++ ; i > 0 ; i--)
            elementData[i] = elementData[i-1];
        elementData[0] = e;
        resize();
    }

    public void addLast(E e){
        if(e == null) throw new NullPointerException();
        elementData[size++] = e;
        resize();
    }

    public E removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        E first = (E) elementData[0];
        for(int i = 0 ; i < size-1 ; i++)
            elementData[i] = elementData[i+1];
        size--;
        resize();
        return first;
    }

    public E removeLast(){
        if(isEmpty()) throw new NoSuchElementException();
        resize();
        return (E) elementData[size--];
    }



    private void resize(){
        if(size == elementData.length){
            Object[] copy = new Object[2*elementData.length];
            for(int i = 0 ; i < size ; i++){
                copy[i] = elementData[i];
            }
            elementData = copy;
        } else if (size <= elementData.length/4){
            Object[] copy = new Object[elementData.length/2];
            for(int i = 0 ; i < size ; i++){
                copy[i] = elementData[i];
            }
            elementData = copy;
        }
    }


    public String toString(){
        String s = "[";
        for(int i = 0; i < size; i++)
            s += elementData[i] + ",";
        s = s.substring(0,s.length()-1)+"]";
        return s;
    }


    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if(!hasNext()) throw new NoSuchElementException();
                return (E) elementData[index++];
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }

        };
        return iterator;
    }
}

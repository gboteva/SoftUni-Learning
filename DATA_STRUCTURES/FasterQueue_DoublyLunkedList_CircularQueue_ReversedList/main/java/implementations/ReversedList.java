package implementations;

import java.util.ArrayDeque;
import java.util.Iterator;

public class ReversedList<E> implements Iterable<E>{
    private final static int INITIAL_CAPACITY = 2;

    private Object[] elements;
    private int head;
    private int tail;
    private int size;

    public ReversedList() {
        elements = new Object[INITIAL_CAPACITY];
        this.head = this.tail = this.elements.length-1;
    }

    public void add(E element) {
        if (this.head < 0) {
            this.elements = grow();
        }

        this.elements[head] = element;
        this.head--;

        this.size++;
    }

    private Object[] grow() {
        Object[] temp = new Object[this.elements.length * 2];

        int begin = temp.length - size;

        int index = head + 1;

        for (int i = begin; index <= tail; i++) {
            temp[i] = this.elements[index];
            index ++;
        }

        head = begin-1;
        tail = temp.length -1;
        return temp;
    }

    public E get(int index) {
        int realIndex = head + 1 + index;
        checkIndex(realIndex);
        return (E) this.elements[realIndex];
    }

    private void checkIndex(int realIndex) {
        if (realIndex <= head || realIndex > tail || this.size == 0){
            throw new IndexOutOfBoundsException("Index " + (realIndex - head -1 )+ " is out of bounds for length " + size);
        }
    }

    public void removeAt(int index) {
        int realIndex = tail - index;
        checkIndex(realIndex);

        this.elements[realIndex] = null;

        int middle = size / 2;

        if (index < middle){
            // left of the middle
            for (int i = realIndex; i > head + 1 ; i--) {
                this.elements[i] = this.elements[i-1];
            }
            head++;
            this.elements[head] = null;
        }else{
            //right of the middle
            for (int i = realIndex; i < tail; i++) {
                this.elements[i] = this.elements[i+1];
            }
            trimLastElement(head+1);
            tail--;
        }

        this.size --;
    }

    private void trimLastElement(int begin) {
        Object[] trim = new Object[this.elements.length -1];
        for (int i = begin; i < tail ; i++) {
            trim[i] = this.elements[i];
        }
        this.elements = trim;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.elements.length;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = head + 1;
            @Override
            public boolean hasNext() {
                return index <= tail;
            }

            @Override
            public E next() {
                return (E) get(index++);
            }
        };
    }

}

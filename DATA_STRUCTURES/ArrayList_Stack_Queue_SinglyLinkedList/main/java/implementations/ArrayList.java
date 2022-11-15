package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private final static int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    private Object[] grow() {
        Object[] tempArray = new Object[this.elements.length * 2];

        for (int i = 0; i < this.elements.length; i++) {
            tempArray[i] = this.elements[i];
        }
        return tempArray;
    }

    private void ensureCapacity() {
        if (this.elements.length == size) {
            this.elements = grow();
        }
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        this.elements[size] = element;
        size++;
        return true;
    }


    @Override
    public boolean add(int index, E element) {
        checkIndex(index);
        ensureCapacity();
        Object[] tempArray = new Object[this.elements.length];
        for (int i = 0; i < index; i++) {
            tempArray[i] = this.elements[i];
        }
        tempArray[index] = element;
        for (int i = index+1; i <= this.size ; i++) {
            tempArray[i] = this.elements[i-1];
        }
        size++;
        this.elements = tempArray;
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException (String.format("Index out of bounds: %d for size %s", index, this.size));
        }
    }

    private E getElement(int index) {
        checkIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E get(int index) {
        return this.getElement(index);
    }

    @Override
    public E set(int index, E element) {
       checkIndex(index);
       E oldElement = this.getElement(index);
       this.elements[index] = element;
       return oldElement;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = this.getElement(index);

        Object[] tempArray = new Object[this.elements.length];
        for (int i = 0; i < index; i++) {
            tempArray[i] = this.elements[i];
        }
        for (int i = index; i < this.size -1 ; i++) {
            tempArray[i] = this.elements[i+1];
        }

        this.size--;

        this.elements = tempArray;

        if (this.elements.length >= this.size * 4){
           shrink();
        }

        return removedElement;
    }

    private void shrink() {
        Object[] tempArr = new Object[this.elements.length/ 2];
        for (int i = 0; i < this.size ; i++) {
            tempArr[i] = this.elements[i];
        }
        this.elements = tempArr;
    }

    @Override
    public int size() {
        return this.size;
    }

//    public int getLength(){
//        return this.elements.length;
//    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0){
            return true;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}

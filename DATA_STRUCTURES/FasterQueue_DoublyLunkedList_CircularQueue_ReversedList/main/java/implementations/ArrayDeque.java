package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        int middle = this.elements.length / 2;
        this.tail = this.head = middle;
    }

    @Override
    public void add(E element) {
        if (this.tail >= this.elements.length - 1) {
            this.elements = grow();
        }
        this.elements[this.tail++] = element;

        this.size++;
    }

    private Object[] grow() {
        int newCapacity = this.elements.length * 2;

        Object[] newElements = new Object[newCapacity];

        int middle = newCapacity / 2;
        int startIndex = middle - this.size / 2;

        int index = this.head;
        for (int i = startIndex; index < tail; i++) {
            newElements[i] = this.elements[index++];
        }

        this.head = startIndex;
        this.tail = head + size;
        return newElements;
    }

    @Override
    public void offer(E element) {
        this.addLast(element);
    }

    @Override
    public void addFirst(E element) {
        if (isEmpty()) {
            add(element);
        } else {
            if (this.head == 0) {
                this.elements = grow();
            }
            this.elements[--this.head] = element;
            this.size++;
        }
    }

    @Override
    public void addLast(E element) {
        this.add(element);
    }

    @Override
    public void push(E element) {
        this.addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = index + head;
        checkIndex(realIndex);

        if (head - 1 < 0) {
            this.elements = grow();
        }

        if (index == 0) {
            this.elements[head - 1] = element;
        } else {
            for (int i = head - 1; i < realIndex - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[realIndex - 1] = element;
        }

        head = head - 1;
        size++;
    }

    @Override
    public void set(int index, E element) {
        int realIndex = index + head;
        checkIndex(realIndex);
        this.elements[realIndex] = element;
    }

    private void checkIndex(int index) {
        if (index < head || index > tail - 1) {
            throw new IndexOutOfBoundsException("Index " + (index - head) + " is out of bounds for length " + size);
        }
    }

    @Override
    public E peek() {
        if (!isEmpty()) {
            return getElement(head);
        }
        return null;
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E get(int index) {
        int realIndex = index + head;
        checkIndex(realIndex);
        return getElement(realIndex);
    }

    private E getElement(int index) {
        return (E) this.elements[index];
    }

    @Override
    public E get(Object object) {
        for (int i = head; i < tail; i++) {
            if (this.getElement(i).equals(object)) {
                return getElement(i);
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        int realIndex = this.head + index;
        checkIndex(realIndex);

        E element = getElement(realIndex);
        this.elements[realIndex] = null;

        for (int i = realIndex; i < tail; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.tail--;
        size--;
        return element;
    }

    @Override
    public E remove(Object object) {
        for (int i = this.head; i < this.tail; i++) {
            if (this.elements[i].equals(object)) {
                E element = this.getElement(i);
                this.elements[i] = null;

                for (int j = i; j < this.tail - 1; j++) {
                    this.elements[j] = this.elements[j + 1];
                }
                this.elements[this.tail - 1] = null;
                this.tail--;
                this.size--;
                return element;
            }

        }

        return null;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            E element = getElement(head);
            this.elements[head] = null;
            this.head++;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()) {
            E element = getElement(tail - 1);
            this.elements[tail - 1] = null;
            this.tail--;
            this.size--;
            return element;
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        Object[] trim = new Object[size];
        int index = 0;
        for (int i = head; i < tail; i++) {
            trim[index] = this.elements[i];
            index++;
        }
        this.elements = trim;
        this.head = 0;
        this.tail = size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = head;

            @Override
            public boolean hasNext() {
                return this.index < tail;
            }

            @Override
            public E next() {
                return getElement(index++);
            }
        };

    }
}

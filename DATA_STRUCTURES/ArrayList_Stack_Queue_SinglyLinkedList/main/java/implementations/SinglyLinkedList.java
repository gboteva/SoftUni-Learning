package implementations;

import interfaces.LinkedList;

import java.util.Iterator;


public class SinglyLinkedList<E> implements LinkedList<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        Node<E> temp = head;

        head = newNode;

        head.next = temp;

        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.head == null) {
            head = newNode;
        } else {
            Node<E> current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }
        size++;
    }

    private void ensureNotEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("List is empty!");
        }
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        E element = head.element;

        head = head.next;

        size--;

        return element;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();

        if (this.size == 1) {
            E element = this.head.element;
            this.head = null;

            return element;
        }

        Node<E> preLast = this.head;
        Node<E> toRemove = this.head.next;

        while (toRemove.next != null){
            preLast = toRemove;
            toRemove = toRemove.next;
        }

        preLast.next = null;

        this.size--;

        return toRemove.element;

    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        Node<E> current = head;

        while (current.next != null) {
            current = current.next;
        }

        return current.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                return head != null;
            }

            @Override
            public E next() {
                E next = head.element;
                Node<E> temp = head.next;
                current = head = temp;
                return next;
            }
        };
    }
}

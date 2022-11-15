package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private static class Node<E> {
        private E element;
        private Node<E> previous;

        public Node(E value) {
            this.element = value;
        }
    }

    private Node<E> topElement;
    private int size;

    public Stack() {
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);

        newNode.previous = topElement;

        topElement = newNode;

        this.size++;
    }

    @Override
    public E pop() {
        ensureNotEmpty();

        E oldElement = topElement.element;

        Node<E> temp = topElement.previous;

        this.topElement = temp;

        this.size--;

        return oldElement;
    }

    private void ensureNotEmpty() {
        if (this.size == 0){
            throw new IllegalStateException("The stack is empty");
        }
    }

    @Override
    public E peek() {
       ensureNotEmpty();
       return topElement.element;
    }

    @Override
    public int size() {
        return this.size;
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
            private Node<E> currentTop = topElement;
            @Override
            public boolean hasNext() {
                return currentTop !=null;
            }

            @Override
            public E next() {
                E element = currentTop.element;
                this.currentTop = this.currentTop.previous;
                return element;
            }
        };
    }
}

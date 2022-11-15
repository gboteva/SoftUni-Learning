package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head;
    private int size;

    public Queue() {
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (head == null){
            head = newNode;
        }else {
            Node<E> currentNode = head;

            while (currentNode.next !=null){
                currentNode = currentNode.next;
            }

            currentNode.next = newNode;
        }

        size++;
    }

    @Override
    public E poll() {
       ensureNotEmpty();

       E element = head.element;

       Node<E> temp = head.next;

       head = temp;

       size--;

       return element;
    }

    private void ensureNotEmpty() {
        if (this.size == 0){
            throw new IllegalStateException("Queue is empty!");
        }
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.head.element;
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
            Node<E> currentElement = head;

            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public E next() {
                E element = currentElement.element;
                this.currentElement = this.currentElement.next;
                return element;
            }
        };
    }
}

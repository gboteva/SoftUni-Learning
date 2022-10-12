package Jar;

import java.util.ArrayDeque;

public class Jar <T> {
    ArrayDeque<T> elements;

    public Jar() {
        this.elements = new ArrayDeque<>();
    }

    public void add(T element){
        this.elements.push(element);
    }

    public T remove (){
        return this.elements.pop();
    }
}

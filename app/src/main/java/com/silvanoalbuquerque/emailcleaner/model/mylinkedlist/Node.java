package com.silvanoalbuquerque.emailcleaner.model.mylinkedlist;

import java.io.Serializable;

public class Node<T> implements Serializable {

    private static final long serialVersionUID = -7748740789523661745L;

    private T data;
    private Node<T> next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

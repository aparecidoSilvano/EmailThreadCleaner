package com.silvanoalbuquerque.emailcleaner.model.mylinkedlist;

import java.io.Serializable;

public class MyLinkedList<T> implements Serializable {

    private static final long serialVersionUID = -4939897577054246990L;

    private Node<T> head;
    private int countElements;

    public void add(T data) {
        Node<T> node = new Node<>();
        node.setData(data);
        node.setNext(null);

        if (head == null) {
            head = node;

        } else {
            Node<T> currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(node);
        }

        countElements++;
    }

    public T get(int index) {
        if (index < 0 || index >= countElements) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node;
        if (head != null) {
            node = head;
            for (int i = 0; i < index; i++) {
                if (node.getData() == null) {
                    return null;
                }

                node = node.getNext();
            }

            return node.getData();
        }

        return null;
    }

    public void remove(int index) {
        if (index < 0 || index >= countElements) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.getNext();
        } else {
            Node<T> currentNode = head;
            Node<T> auxNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }

            auxNode = currentNode.getNext();
            currentNode.setNext(auxNode.getNext());
        }

        countElements--;
    }

    public int removeDuplicates() {
        int countDuplicates = 0;
        Node<T> currentNode, nextNode;
        currentNode = head;

        while (currentNode != null && currentNode.getNext() != null) {
            nextNode = currentNode;

            while (nextNode.getNext() != null) {

                if (currentNode.getData().equals(nextNode.getNext().getData())) {
                    nextNode.setNext(nextNode.getNext().getNext());
                    countDuplicates++;
                    countElements--;
                } else {
                    nextNode = nextNode.getNext();
                }
            }

            currentNode = currentNode.getNext();
        }

        return countDuplicates;
    }

    public int size() {
        return countElements;
    }
}

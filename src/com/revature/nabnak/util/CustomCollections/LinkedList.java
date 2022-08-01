package com.revature.nabnak.util.CustomCollections;

public class LinkedList<E> implements List<E>{

    private int size;
    private Node<E> head;
    private Node<E> tail;

    @Override
    public boolean add(E element) {

        if(element == null) return false;

        Node<E> newNode = new Node<>(element);
        if(head == null) tail = head = newNode;
        else {
            tail.nextNode = newNode; // current tail now has a nextNode instead null that is this newNode
            tail = newNode; // the tail is now reassigned to be the newNode as it was just added
        }
        size++;

        return true;
    }

    @Override
    public boolean contains(E element) {
        Node<E> runner = head;

        while(runner != null){
            if(runner.data.equals(element)) return true;

            runner = runner.nextNode;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E element) {
        Node<E> prevNode = null;
        Node<E> currentNode = head;

        if(size == 0 || element == null) return false;

        // parse the linkedlist to find the right one, how? while currentNode != null, or forLoop of size
        // currentNode == null

        for(int i = 0; i < size; i++){

            if(currentNode.data != null && currentNode.data.equals(element)) {
                if (currentNode == head) {
                    head = currentNode.nextNode; // replace the head that will be removed with the next head in the linked list
                } else if(currentNode == tail){
                    tail = prevNode;
                    prevNode.nextNode = null;
                } else {
                    prevNode.nextNode = currentNode.nextNode;
                }

                size--;
                return true;
            }
            prevNode = currentNode; // this re-assignment must come first so the currentNode is save as the previous
            currentNode = currentNode.nextNode; // then we move onto the next node
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = head;
        for(int i = 0; i<= index; i++){
            if(i == index){
                return currentNode.data;
            }
            // we have to change to the next node in order ot progress down the list
            currentNode = currentNode.nextNode;
        }

        return null;
    }

    private static class Node<T>{
        T data;
        Node<T> nextNode;

        public Node(T data){
            this.data = data;
        }
    }
}

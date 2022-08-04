package com.revature.nabnak.util.CustomCollections;

public interface Collection<E> {

    boolean add(E element);

    boolean contains(E element);

    boolean isEmpty();

    boolean remove(E element);

    int size();
}

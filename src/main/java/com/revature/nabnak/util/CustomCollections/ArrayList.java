package com.revature.nabnak.util.CustomCollections;

import java.util.Arrays;

/**
 * Resizable-array implementation of the List interface.
 * Each ArrayList instance has a capacity. The capacity is the size
 * of the array used to store the elements in the list. It is always at least as large
 * as the list size. As elements are added to an ArrayList, its capacity grows
 * automatically.
 *
 * @param <T> the type of elements maintained by this list
 */
public class ArrayList<T> implements List<T> {

    protected int size;
    protected Object[] elements;


    @Override
    public boolean add(T element) {
        return false;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
/*
Name: Clinton J Schultz
Prof: Dr. Jeffrey Ward
Assignment: Lab#4 - BSTs
Date: 10/17/2020

This is a simple javafx program that will allow a user to enter numbers to be used in a variety
of applications pertaining to binary search trees (BSTs). There are some modifications within
the code that help visually guide the user through every step of the operation, such as shaded
and orange-highlighted nodes that help us to see what is happening more easily. The user gets
to determine the order of obtaining the data, as well.
 */

import java.util.Collection;

public interface Tree<E> extends Collection<E> {
    /** Return true if the element is in the tree */
    public boolean search(E e);

    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e);

    /** Delete the specified element from the tree
     * Return true if the element is deleted successfully */
    public boolean delete(E e);

    /** Get the number of nodes in the tree */
    public int getSize();

    /** Inorder traversal from the root*/
    public default void inorder() {
        //throw new UnsupportedOperationException();
    }

    /** Postorder traversal from the root */
    public default void postorder() {
    }

    /** Preorder traversal from the root */
    public default void preorder() {
    }

    @Override /** Return true if the tree is empty */
    public default boolean isEmpty() {
        return size() == 0;
    };

    @Override
    public default boolean contains(Object e) {
        return search((E)e);
    }

    @Override
    public default boolean add(E e) {
        return insert(e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E)e);
    }

    @Override
    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default Object[] toArray() {
        // Left as an exercise
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        // Left as an exercise
        return null;
    }
}

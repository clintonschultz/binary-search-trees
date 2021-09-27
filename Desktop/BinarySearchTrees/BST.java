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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BST() {
    }

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                return true; // e is found
        }

        return false;
    }

    @Override /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
            //root = new TreeNode<>(e);	// Question:  Why not do it this way?  It would work.
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
                //parent.left = new TreeNode<>(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override /** Inorder traversal from the root */
    public void inorder() {
        inorder(root);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }
    // Return inorder list
    public List<E> inorderList() {
        List<E> list = new ArrayList<>();
        inorderList(root, list);
        return list;
    }
    // Add elements to list following inorder sequence
    private void inorderList(TreeNode<E> root, List<E> list) {
        if (root != null) {
            inorderList(root.left, list);
            list.add(root.element);
            inorderList(root.right, list);
        }
    }

    @Override /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }
    // Return postorder list
    public List<E> postorderList() {
        List<E> list = new ArrayList<E>();
        postorderList(root, list);
        return list;
    }
    // Add elements to list following postorder sequence
    private void postorderList(TreeNode<E> root, List<E> list) {
        if (root != null) {
            postorderList(root.left, list);
            postorderList(root.right, list);
            list.add(root.element);
        }
    }

    @Override /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }
    // Return preorder list
    public List<E> preorderList() {
        List<E> list = new ArrayList<E>();
        preorderList(root, list);
        return list;
    }
    // Add elements to list following preorder sequence
    private void preorderList(TreeNode<E> root, List<E> list) {
        if (root != null) {
            list.add(root.element);
            preorderList(root.left, list);
            preorderList(root.right, list);
        }
    }
    // Return height of tree
    public int height() {
        return height(root);
    }
    // Get height of tree
    private int height(TreeNode<E> root) {
        if (root == null) {
            return -1;
        }
        else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    // Return list of elements in the tree in breadth-first order
    public List<E> breadthFirstOrderList() {
        Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
        queue.add(root);
        List<E> list = new ArrayList<E>();

        while (queue.size() != 0) {
            TreeNode<E> temp = queue.poll();
            list.add(temp.element);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        return list;
    }


    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }

    @Override /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    @Override /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    public java.util.Iterator<E> iterator(int index) {
        return new InorderIterator(index);
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Index of next element in the iteration

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        public InorderIterator(int index) {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();
            inorder();
            current = index;
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override /** More elements for traversing? */
        public boolean hasNext() {
            return current < list.size();
        }

        @Override /** Get the current element and move to the next */
        public E next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return list.get(current++);
        }

        @Override /** Remove the element most recently returned */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}

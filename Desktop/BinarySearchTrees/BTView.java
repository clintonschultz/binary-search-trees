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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView<E> extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; // Gap between successive levels in the tree

    BTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }
    public void setShadedNodes(ArrayList<BST.TreeNode<Integer>> shadedNodes) {
        this.getChildren().clear(); // Clear pane
        if (tree.getRoot() != null) {
            // Show tree
            setShadedNodes(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4, shadedNodes);
        }
    }
    private void setShadedNodes(BST.TreeNode<Integer> root, double x, double y,
                                double hGap, ArrayList<BST.TreeNode<Integer>> shadedNodes) {
        if (root.left != null) {
            // Draw line to left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw left subtree
            setShadedNodes(root.left, x - hGap, y + vGap, hGap / 2, shadedNodes);
        }
        Circle circle = new Circle(x, y, radius);

        if (shadedNodes.contains(root)) {
            circle.setFill(Color.ORANGE);
        }
        else {
            circle.setFill(Color.WHITE);
        }
        circle.setStroke(Color.BLACK);
        this.getChildren().addAll(circle, new Text(x - 4, y + 4, root.element.toString()));
    }

    public void setStatus(String msg) {
        this.getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }

    /** Display a subtree rooted at position (x, y) */
    private void displayTree(BST.TreeNode<Integer> root,
                             double x, double y, double hGap) {
        ArrayList<BST.TreeNode<E>> shadedNodes = new ArrayList<BST.TreeNode<E>>();

        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        if (shadedNodes.contains(root)) {
            circle.setFill(Color.ORANGE);
        }
        else {
            circle.setFill(Color.WHITE);
        }
        circle.setStroke(Color.BLACK);
        this.getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.element.toString()));
    }
}

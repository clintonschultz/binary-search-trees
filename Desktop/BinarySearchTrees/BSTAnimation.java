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

import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BSTAnimation<TreeNode> extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btSearch = new Button("Search");
        Button btInorder = new Button("Inorder");
        Button btPreorder = new Button("Preorder");
        Button btPostorder = new Button("Postorder");
        Button btBreadthFirst = new Button("Breadth-first");
        Button btHeight = new Button("Height");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btSearch, btInorder,
                btPreorder, btPostorder, btBreadthFirst, btHeight);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();   // Clears the old status message
                view.setStatus(key + " is already in the tree");
            }
            else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();    // Clears the old status message
                view.setStatus(key + " is not in the tree");
            }
            else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        btSearch.setOnAction(e -> {
                    int key = Integer.parseInt(tfKey.getText());
                    if (!tree.search(key)) {
                        view.setShadedNodes(tree.path(key));
                        view.setStatus(key + " is not in the tree");
                    }
                    else {
                        tree.search(key);
                        view.setShadedNodes(tree.path(key));
                        view.setStatus("Found " + key + " in tree");
                    }
        });

        btInorder.setOnAction(e -> {
            List<Integer> list = tree.inorderList();
            String inorderList = list.toString();
            view.displayTree();
            view.setStatus("Inorder traversal: " + inorderList);
        });

        btPreorder.setOnAction(e -> {
            List<Integer> list = tree.preorderList();
            String preorderList = list.toString();
            view.displayTree();
            view.setStatus("Preorder traversal: " + preorderList);
        });

        btPostorder.setOnAction(e -> {
            List<Integer> list = tree.postorderList();
            String postorderList = list.toString();
            view.displayTree();
            view.setStatus("Postorder traversal: " + postorderList);
        });

        btBreadthFirst.setOnAction(e -> {
            List<Integer> queue = tree.breadthFirstOrderList();
            String breadthFirstOrderList = queue.toString();
            view.displayTree();
            view.setStatus("Breadth-first traversal: " + breadthFirstOrderList);
        });

        btHeight.setOnAction(e -> {
            view.displayTree();
            int height = tree.height();
            String treeHeight = String.valueOf(height);
            view.setStatus("Tree height: " + treeHeight);
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 700, 400);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}


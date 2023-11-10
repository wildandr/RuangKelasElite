import java.util.Arrays;
import java.util.ArrayList;


public class BST {
    private NodeBST root;

    public BST() {
        this.root = null;
    }

    public void insert(int classId) {
        root = insertRec(root, classId);
    }

    public void delete(int classId) {
        root = deleteRec(root, classId);
    }

    public int findMin() {
        NodeBST minNode = findMinRec(root);
        return (minNode != null) ? minNode.getClassId() : -1;
    }

    public void exchange(int currentClassId, int prevClassId, int nextClassId) {
        delete(currentClassId);
        insert(prevClassId);
        insert(nextClassId);
    }

    private NodeBST insertRec(NodeBST root, int classId) {
        if (root == null) {
            root = new NodeBST(classId);
            return root;
        }

        if (classId < root.getClassId()) {
            root.setLeft(insertRec(root.getLeft(), classId));
        } else if (classId > root.getClassId()) {
            root.setRight(insertRec(root.getRight(), classId));
        }

        return root;
    }

    private NodeBST deleteRec(NodeBST root, int classId) {
        if (root == null) {
            return root;
        }

        if (classId < root.getClassId()) {
            root.setLeft(deleteRec(root.getLeft(), classId));
        } else if (classId > root.getClassId()) {
            root.setRight(deleteRec(root.getRight(), classId));
        } else {
            // Node with only one child or no child
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // Node with two children
            root.setClassId(minValue(root.getRight()));

            // Delete the in-order successor
            root.setRight(deleteRec(root.getRight(), root.getClassId()));
        }

        return root;
    }

    private int minValue(NodeBST root) {
        int minValue = root.getClassId();
        while (root.getLeft() != null) {
            minValue = root.getLeft().getClassId();
            root = root.getLeft();
        }
        return minValue;
    }

    private NodeBST findMinRec(NodeBST root) {
        if (root == null) {
            return null;
        }

        while (root.getLeft() != null) {
            root = root.getLeft();
        }

        return root;
    }
}

public class NodeBST {
    private int classId;
    private NodeBST left;
    private NodeBST right;
    private int height; // Tinggi node untuk AVL Tree

    public NodeBST(int classId) {
        this.classId = classId;
        this.left = null;
        this.right = null;
        this.height = 1; // Tinggi awal node baru adalah 1
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public NodeBST getLeft() {
        return left;
    }

    public void setLeft(NodeBST left) {
        this.left = left;
        updateHeight();
    }

    public NodeBST getRight() {
        return right;
    }

    public void setRight(NodeBST right) {
        this.right = right;
        updateHeight();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Update tinggi node berdasarkan tinggi subtree kiri dan kanan
    private void updateHeight() {
        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;
        height = Math.max(leftHeight, rightHeight) + 1;
    }
}

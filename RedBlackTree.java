public class RedBlackTree {
    static final boolean RED = false;
    static final boolean BLACK = true;
    Node root;

    public Node searchNode(int value) {
        Node node = root;
        while (node != null) {
            if (value == node.value) {
                return node;
            } else if (value < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    public void insertNode(int value) {
        Node node = root;
        Node parent = null;
        while (node != null) {
            parent = node;
            if (value < node.value) {
                node = node.left;
            } else if (value > node.value) {
                node = node.right;
            } else {
                throw new IllegalArgumentException("Tree already contains a node with value " + value);
            }
        }

        Node newNode = new Node(value);
        newNode.color = RED;
        if (parent == null) {
            root = newNode;
        } else if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        balanceTree(newNode);
    }

    private void balanceTree(Node node) {
        Node parent = node.parent;
        if (parent == null) {
            return;
        }
        if (parent.color == BLACK) {
            return;
        }
        Node previousParent = parent.parent;
        if (previousParent == null) {
            parent.color = BLACK;
            return;
        }
        Node neighbourNode = getNeighbourNode(parent);
        if (neighbourNode != null && neighbourNode.color == RED) {
            parent.color = BLACK;
            previousParent.color = RED;
            neighbourNode.color = BLACK;
            balanceTree(previousParent);
        }
        else if (parent == previousParent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(previousParent);
            parent.color = BLACK;
            previousParent.color = RED;
        }
        else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(previousParent);
            parent.color = BLACK;
            previousParent.color = RED;
        }
    }

    private Node getNeighbourNode(Node parent) {
        Node previousParent = parent.parent;
        if (previousParent.left == parent) {
            return previousParent.right;
        } else if (previousParent.right == parent) {
            return previousParent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its previous parent");
        }
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.left = node;
        node.parent = rightChild;
        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    public static class Node {
        int value;

        Node left;
        Node right;
        Node parent;

        boolean color;

        public Node(int value) {
            this.value = value;
        }
    }
}

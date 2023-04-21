public class SplayTree<T extends Comparable<T>> implements EstructuraArbol<T> {

    private Node<T> root;
    private int count;

    private static class Node<T> {
        T value;
        Node<T> left, right;

        Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public SplayTree() {
        root = null;
        count = 0;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            count++;
            return;
        }

        root = splay(root, value);
        int cmp = value.compareTo(root.value);
        if (cmp < 0) {
            newNode.left = root.left;
            newNode.right = root;
            root.left = null;
            root = newNode;
        } else if (cmp > 0) {
            newNode.right = root.right;
            newNode.left = root;
            root.right = null;
            root = newNode;
        } else {
            // value already exists
            return;
        }
        count++;
    }

    public T get(T key) {
        root = splay(root, key);
        if (root != null && root.value.compareTo(key) == 0) {
            return root.value;
        }
        return null;
    }

    public T remove(T key) {
        Node<T> temp = root;
        if (root == null) {
            return null;
        }

        root = splay(root, key);
        if (root.value.compareTo(key) != 0) {
            // key not found
            root = temp;
            return null;
        }

        T value = root.value;
        if (root.left == null) {
            root = root.right;
        } else {
            Node<T> newRoot = splay(root.left, key);
            newRoot.right = root.right;
            root = newRoot;
        }
        count--;
        return value;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private Node<T> splay(Node<T> root, T key) {
        if (root == null || root.value.compareTo(key) == 0) {
            return root;
        }
    
        if (root.value.compareTo(key) > 0) {
            if (root.left == null) {
                return root;
            }
            if (root.left.value.compareTo(key) > 0) {
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            } else if (root.left.value.compareTo(key) < 0) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) {
                    root.left = rotateLeft(root.left);
                }
            }
            if (root.left == null) {
                return root;
            }
            return rotateRight(root);
        } else {
            if (root.right == null) {
                return root;
            }
            if (root.right.value.compareTo(key) > 0) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) {
                    root.right = rotateRight(root.right);
                }
            } else if (root.right.value.compareTo(key) < 0) {
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            }
            if (root.right == null) {
                return root;
            }
            return rotateLeft(root);
        }
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }
    
    private Node<T> rotateRight(Node<T> node) {
        Node<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

}

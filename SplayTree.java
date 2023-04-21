
public class SplayTree<K extends Comparable<K>, V> implements EstructuraArbol<K, V> {
    private Node root;
    private int count;

    private class Node {
        K key;
        V value;
        Node left, right, parent;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void add(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            count++;
            return;
        }

        Node newNode = new Node(key, value);
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;

            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                // Key already exists, update value and splay the node to the root
                node.value = value;
                splay(node);
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        newNode.parent = parent;
        count++;
        splay(newNode);
    }

    public V get(K key) {
        Node node = search(key);

        if (node == null) {
            return null;
        }

        splay(node);
        return node.value;
    }

    public V remove(K key) {
        Node node = search(key);

        if (node == null) {
            return null;
        }

        splay(node);

        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node successor = minimum(node.right);

            if (successor.parent != node) {
                transplant(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }

            transplant(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }

        count--;
        return node.value;
    }

    private void transplant(Node node1, Node node2) {
        if (node1.parent == null) {
            root = node2;
        } else if (node1 == node1.parent.left) {
            node1.parent.left = node2;
        } else {
            node1.parent.right = node2;
        }
    
        if (node2 != null) {
            node2.parent = node1.parent;
        }
    }
    
    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
    
        return node;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private Node search(K key) {
        Node node = root;

        while (node != null) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                return node;
            }
        }

        return null;
    }

    private void splay(Node node) {
        while (node.parent != null) {
            Node parent = node.parent;
            Node grandparent = parent.parent;
    
            if (grandparent == null) {
                // Zig operation
                if (node == parent.left) {
                    rotateRight(parent);
                } else {
                    rotateLeft(parent);
                }
            } else if (node == parent.left && parent == grandparent.left) {
                // Zig-zig operation
                rotateRight(grandparent);
                rotateRight(parent);
            } else if (node == parent.right && parent == grandparent.right) {
                // Zig-zig operation
                rotateLeft(grandparent);
                rotateLeft(parent);
            } else if (node == parent.right && parent == grandparent.left) {
                // Zig-zag operation
                rotateLeft(parent);
                rotateRight(grandparent);
            } else {
                // Zig-zag operation
                rotateRight(parent);
                rotateLeft(grandparent);
            }
        }
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;
    
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
    
        node.right = rightChild.left;
        rightChild.left = node;
        node.parent = rightChild;
        rightChild.parent = parent;
    
        if (parent != null) {
            if (parent.left == node) {
                parent.left = rightChild;
            } else {
                parent.right = rightChild;
            }
        } else {
            root = rightChild;
        }
    }
    
    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;
    
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
    
        node.left = leftChild.right;
        leftChild.right = node;
        node.parent = leftChild;
        leftChild.parent = parent;
    
        if (parent != null) {
            if (parent.left == node) {
                parent.left = leftChild;
            } else {
                parent.right = leftChild;
            }
        } else {
            root = leftChild;
        }
    }   
    

}

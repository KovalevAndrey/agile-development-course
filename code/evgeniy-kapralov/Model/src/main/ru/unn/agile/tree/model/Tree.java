package ru.unn.agile.tree.model;

public class Tree {
    private Tree left;
    private Tree right;
    private int nodeKey;

    public Tree(int key) {
        nodeKey = key;
    }

    public int getKey() {
        return nodeKey;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tree)) {
            return false;
        }
        else {
            Tree tree = (Tree)obj;
            if (tree.nodeKey == nodeKey) {
                if (tree.left == left && tree.right == right) {
                    return true;
                }
                else if (tree.left == null && tree.right != null && tree.right.equals(right))
                {
                    return true;
                }
                else if (tree.right == null && tree.left != null && tree.left.equals(left))
                {
                    return true;
                }
                else if (tree.left == null || tree.right == null)
                {
                    return false;
                }
                else {
                    if (tree.left.equals(left) && tree.right.equals(right))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void insert(int key) {
        if (key < nodeKey) {
            if (left == null) {
                left = new Tree(key);
            }
            else {
                left.insert(key);
            }
        }
        else {
            if (right == null) {
                right = new Tree(key);
            }
            else {
                right.insert(key);
            }
        }
    }

    public Tree find(int key) {
        if (key == nodeKey) {
            return this;
        }

        if (left != null && key < nodeKey) {
            return left.find(key);
        }
        else if (right != null && key >= nodeKey) {
            return right.find(key);
        }

        return null;
    }
}

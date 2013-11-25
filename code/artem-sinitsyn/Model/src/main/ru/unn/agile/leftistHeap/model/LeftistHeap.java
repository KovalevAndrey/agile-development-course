package ru.unn.agile.leftistHeap.model;

public class LeftistHeap {
    private Node root;
    private int count;

    public Node getRoot() {
        return root;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        if (root == null)
            return true;
        else
            return false;
    }

    public LeftistHeap() {
        root = null;
        count = 0;
    }

    public LeftistHeap(Pair data) {
        root = new Node();
        root.setData(data);
        count = 1;
    }

    public void setFree() {
        root = null;
        count = 0;
    }

    public Pair getMin() {
        if (!isEmpty())
            return root.getData();
        else
            throw new RuntimeException("The heap is empty");
    }

    public void deleteMin() {
        if (!isEmpty()) {
            root = merge(root.getLeftChild(), root.getRightChild());
            count--;
        }
        else
            throw new RuntimeException("The heap is empty");
    }

    public void add(Pair data) {
        root = merge(root, new Node(data));
        count++;
    }

    public void merge(LeftistHeap mergingHeap)
    {
        if (this == mergingHeap)
            throw new IllegalArgumentException("The heap can't be merged with itself");

        count = (getCount() + mergingHeap.getCount());

        if (isEmpty()) {
            root = mergingHeap.getRoot();
            mergingHeap.setFree();
            return;
        }
        if (mergingHeap.isEmpty())
            return;

        root = merge(root.getRightChild(), mergingHeap.getRoot());
        mergingHeap.setFree();
    }

    private Node merge(Node firstNode, Node secondNode) {
        if (firstNode == null)
            return secondNode;
        if (secondNode == null)
            return firstNode;

        if (firstNode.getData().getKey() > secondNode.getData().getKey()) {
            Node tmp = firstNode;
            firstNode = secondNode;
            secondNode = tmp;
        }

        firstNode.setRightChild(merge(firstNode.getRightChild(), secondNode));

        if (firstNode.getLeftChild() == null) {
            firstNode.setLeftChild(firstNode.getRightChild());
            firstNode.setRightChild(null);
        }
        else {
            if (firstNode.getLeftChild().getRank() < firstNode.getRightChild().getRank()) {
                Node tmp = firstNode.getLeftChild();
                firstNode.setLeftChild(firstNode.getRightChild());
                firstNode.setRightChild(tmp);
            }
            firstNode.setRank(firstNode.getRightChild().getRank() + 1);
        }

        return  firstNode;
    }
}
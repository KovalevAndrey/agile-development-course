package ru.unn.agile.leftistHeap.model;

import org.junit.Test;
import ru.unn.agile.leftistHeap.model.Node;
import ru.unn.agile.leftistHeap.model.Pair;

import static org.junit.Assert.assertArrayEquals;

public class NodeTests {
    private Node node;

    @Test
    public void isDefaultNodeCorrect() {
        node = new Node();
        assertNodeEquals(new Pair(), 1, null, null);
    }

    @Test
    public void isNodeWithDataCorrect() {
        Pair data = new Pair(1, "Node");
        node = new Node(data);
        assertNodeEquals(data, 1, null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnInvalidRank() {
        node = new Node();
        node.setRank(0);
    }

    private void assertNodeEquals(Pair data, int rank, Node leftChild, Node rightChild) {
        Object[] expected = {
                data.getKey(), data.getValue(),
                rank,
                leftChild, rightChild};
        Object[] actual = {
                node.getData().getKey(), node.getData().getValue(),
                node.getRank(),
                node.getLeftChild(), node.getRightChild()};
        assertArrayEquals(expected, actual);
    }
}

package ru.unn.agile.tree.model;

import org.junit.*;
import static org.junit.Assert.*;

public class TreeTest {
    private Tree tree;
    private int rootKey = 10;

    @Before
    public void initialize() {
        tree = new Tree(rootKey);
    }

    @Test
    public void treeIsNotEqualToNull() {
        assertEquals(false, tree.equals(null));
    }

    @Test
    public void treeIsNotEqualToNotTree() {
        assertEquals(false, tree.equals(new Integer(5)));
    }

    @Test
    public void treeIsEqualToItself() {
        assertEquals(tree, tree);
    }

    @Test
    public void smallTreesWithSameKeysAreEqual() {
        assertEquals(new Tree(3), new Tree(3));
    }

    @Test
    public void treesWithDifferentKeysAreNotEqual() {
        assertEquals(false, (new Tree(3)).equals(new Tree(4)));
    }

    @Test
    public void insertLessThanRootKeyDeepOne() {
        tree.insert(5);
        assertEquals(new Tree(5), tree.getLeft());
    }

    @Test
    public void insertLessThanRootKeyDeepTwo() {
        tree.insert(5);
        tree.insert(3);
        assertEquals(new Tree(3), tree.getLeft().getLeft());
    }

    @Test
    public void insertMoreThanRootKeyDeepOne() {
        tree.insert(15);
        assertEquals(new Tree(15), tree.getRight());
    }

    @Test
    public void insertMoreThanRootKeyDeepTwo() {
        int key = 17;
        tree.insert(15);
        tree.insert(key);
        assertEquals(new Tree(key), tree.getRight().getRight());
    }

    @Test
     public void insertLeftThenRight() {
        tree.insert(5);
        tree.insert(7);
        assertEquals(new Tree(7), tree.getLeft().getRight());
    }

    @Test
    public void insertRightThenLeft() {
        tree.insert(19);
        tree.insert(15);
        assertEquals(new Tree(15), tree.getRight().getLeft());
    }

    @Test
    public void insertRootKey() {
        tree.insert(rootKey);
        assertEquals(new Tree(rootKey), tree.getRight());
    }

    @Test
    public void bigTreesWithSameKeysAreEqual() {
        tree.insert(239);
        tree.insert(17);
        tree.insert(-8);
        tree.insert(-4);
        tree.insert(256);

        Tree secondTree = new Tree(239);
        secondTree.insert(17);
        secondTree.insert(256);

        assertEquals(secondTree, tree.getRight());
    }

    @Test
    public void findRootKey() {
        assertEquals(tree, tree.find(rootKey));
    }

    @Test
    public void findNonExistentKey() {
        assertEquals(null, tree.find(0));
    }

    @Test
    public void findLeftKeyDeepOne() {
        tree.insert(6);
        assertEquals(new Tree(6), tree.find(6));
    }

    @Test
    public void findLeftKeyDeepTwo() {
        tree.insert(8);
        tree.insert(-4);
        assertEquals(new Tree(-4), tree.find(-4));
    }

    @Test
    public void findRightKeyDeepOne() {
        tree.insert(12);
        assertEquals(new Tree(12), tree.find(12));
    }

    @Test
    public void findRightKeyDeepTwo() {
        tree.insert(12);
        tree.insert(23);
        assertEquals(new Tree(23), tree.find(23));
    }

    @Test
    public void findMiddleKey() {
        tree.insert(-5);
        tree.insert(18);
        tree.insert(11);
        tree.insert(7);
        assertEquals(new Tree(7), tree.find(7));
    }

    @Test
    public void findSubTree() {
        tree.insert(140);
        tree.insert(17);
        tree.insert(-8);
        tree.insert(-4);
        tree.insert(140);

        Tree secondTree = new Tree(140);
        secondTree.insert(17);
        secondTree.insert(140);

        assertEquals(secondTree, tree.find(140));
    }
}

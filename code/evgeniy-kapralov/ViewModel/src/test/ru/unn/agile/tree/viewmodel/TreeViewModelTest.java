package ru.unn.agile.tree.viewmodel;

import org.junit.*;
import static org.junit.Assert.*;
import ru.unn.agile.tree.model.Tree;

public class TreeViewModelTest {
    TreeViewModel viewModel;

    @Before
    public void initialize() {
        viewModel = new TreeViewModel();
    }

    @Test
    public void resultStringDefaultValue() {
        assertEquals("Result", viewModel.result);
    }

    @Test
    public void stringIsNull() {
        String str = null;
        assertEquals(false, viewModel.isStringNumber(str));
    }

    @Test
    public void stringIsEmpty() {
        String str = "";
        assertEquals(false, viewModel.isStringNumber(str));
    }

    @Test
    public void stringIsNotNumber() {
        String str = "239ololo";
        assertEquals(false, viewModel.isStringNumber(str));
    }

    @Test
    public void stringIsNumber() {
        String str = "239";
        assertEquals(true, viewModel.isStringNumber(str));
    }

    @Test
    public void parseNullString() {
        String str = null;
        assertEquals(null, viewModel.parseStringToTree(str));
    }

    @Test
    public void parseEmptyString() {
        String str = "";
        assertEquals(null, viewModel.parseStringToTree(str));
    }

    @Test
    public void parseStringWithInvalidFormat() {
        String str = "ol 34";
        assertEquals(null, viewModel.parseStringToTree(str));
    }

    @Test
    public void parseValidString() {
        String str = "3,14,-5,17,0";
        Tree testTree = new Tree(3);
        testTree.insert(14);
        testTree.insert(-5);
        testTree.insert(17);
        testTree.insert(0);

        assertEquals(testTree, viewModel.parseStringToTree(str));
    }

    @Test
    public void invalidTreeString() {
        viewModel.treeValues = "popyachsya";
        viewModel.findActionHandler.onClick();
        assertEquals("Tree string has invalid format", viewModel.result);
    }

    @Test
    public void invalidFindString() {
        viewModel.treeValues = "5,0,-9,11,239,183,4";
        viewModel.findValue = "pysch-pysch";
        viewModel.findActionHandler.onClick();
        assertEquals("Find value is invalid", viewModel.result);
    }

    @Test
    public void findValueNotExist() {
        viewModel.treeValues = "5,0,-9,11,239,183,4";
        viewModel.findValue = "17";
        viewModel.findActionHandler.onClick();
        assertEquals("Value not found", viewModel.result);
    }

    @Test
    public void findValueExist() {
        viewModel.treeValues = "5,0,-9,11,239,183,4";
        viewModel.findValue = "239";
        viewModel.findActionHandler.onClick();
        assertEquals("Tree contains the value", viewModel.result);
    }
}

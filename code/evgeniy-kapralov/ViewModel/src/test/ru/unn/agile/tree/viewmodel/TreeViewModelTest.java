package ru.unn.agile.tree.viewmodel;

import org.junit.*;
import static org.junit.Assert.*;
import ru.unn.agile.tree.model.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeViewModelTest {
    TreeViewModel viewModel;
    FakeLogger fakeLogger;

    @Before
    public void initialize() {
        fakeLogger = new FakeLogger();
        viewModel = new TreeViewModel(fakeLogger);
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

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new TreeViewModel(null);
            fail("Exception was not thrown");
        }
        catch(IllegalArgumentException ex) {
            assertEquals("Logger parameter cannot be null", ex.getMessage());
        }
        catch(Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logEmptyOnStartup() {
        assertEquals(new ArrayList<String>(), viewModel.readLog());
    }

    @Test
    public void logIsNotEmptyOnFind() {
        viewModel.findActionHandler.onClick();
        assertNotEquals(0, viewModel.readLog().size());
    }

    @Test
    public void logContainsFindButtonPressed() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.FIND_BUTTON_PRESSED));
    }

    @Test
    public void logContainsInputParameters() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.INPUT_PARAMETERS));
    }

    @Test
    public void logContainsEmptyTreeValues() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.TREE_VALUES + "<empty>"));
    }

    @Test
    public void logContainsNotEmptyTreeValues() {
        viewModel.treeValues = "5,0,-9,11,239,183,4";
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.TREE_VALUES + viewModel.treeValues));
    }

    @Test
    public void logContainsEmptyFindValue() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.FIND_VALUE + "<empty>"));
    }

    @Test
    public void logContainsNotEmptyFindValue() {
        viewModel.findValue = "17";
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.FIND_VALUE + viewModel.findValue));
    }

    @Test
    public void logContainsProcessStarted() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.PROCESS_STARTED));
    }

    @Test
    public void logContainsProcessFinished() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.PROCESS_FINISHED));
    }

    @Test
    public void logContainsResult() {
        viewModel.findActionHandler.onClick();
        assertEquals(true, viewModel.readLog().contains(TreeViewModel.LogConstants.RESULT + "Tree string has invalid format"));
    }

    @Test
    public void logContainOnlyNecessaryData() {
        ArrayList<String> log = new ArrayList<String>();
        log.add(TreeViewModel.LogConstants.FIND_BUTTON_PRESSED);
        log.add(TreeViewModel.LogConstants.INPUT_PARAMETERS);
        log.add(TreeViewModel.LogConstants.TREE_VALUES + "<empty>");
        log.add(TreeViewModel.LogConstants.FIND_VALUE + "<empty>");
        log.add(TreeViewModel.LogConstants.PROCESS_STARTED);
        log.add(TreeViewModel.LogConstants.PROCESS_FINISHED);
        log.add(TreeViewModel.LogConstants.RESULT + "Tree string has invalid format");

        viewModel.findActionHandler.onClick();
        assertEquals(log, viewModel.readLog());
    }
}

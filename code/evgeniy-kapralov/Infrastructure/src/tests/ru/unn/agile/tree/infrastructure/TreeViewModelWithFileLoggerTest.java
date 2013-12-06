package ru.unn.agile.tree.infrastructure;

import ru.unn.agile.tree.viewmodel.TreeViewModel;
import ru.unn.agile.tree.viewmodel.TreeViewModelTest;

public class TreeViewModelWithFileLoggerTest extends TreeViewModelTest {
    @Override
    public void initialize() {
        FileLogger fileLogger = new FileLogger("./TreeViewModelWithFileLoggerTest.log");
        viewModel = new TreeViewModel(fileLogger);
    }
}

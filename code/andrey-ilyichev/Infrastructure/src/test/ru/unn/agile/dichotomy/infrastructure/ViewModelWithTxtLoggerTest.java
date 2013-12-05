package ru.unn.agile.dichotomy.infrastructure;

import ru.unn.agile.dichotomy.viewmodel.ViewModelTest;

public class ViewModelWithTxtLoggerTest extends ViewModelTest{

	@Override
	public void setUp(){
		logger = new TxtLogger("viewModelWithTxtLoggerTestLog.txt");
	}
}

package ru.unn.agile.dichotomy.viewmodel;

import java.util.ArrayList;

public interface ILogger {
	void addRecord(String s);
	ArrayList <String> getLogList();
	int getLogSize();
 }

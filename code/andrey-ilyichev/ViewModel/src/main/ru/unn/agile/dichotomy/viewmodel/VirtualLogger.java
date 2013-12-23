package ru.unn.agile.dichotomy.viewmodel;

import java.util.ArrayList;

public class VirtualLogger implements ILogger{
	private ArrayList<String> log;
	
	public VirtualLogger(){
		this.log = new ArrayList<String>();
	}
	
	@Override
	public void addRecord(String s) {
		this.log.add(s);
	}

	@Override
	public ArrayList<String> getLogList() {
		return log;
	}

	@Override
	public int getLogSize() {
		return this.log.size();
	}
}

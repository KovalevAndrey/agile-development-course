package ru.unn.agile.dichotomy.infrastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ru.unn.agile.dichotomy.viewmodel.ILogger;

public class TxtLogger implements ILogger {

	private static String fileName;
	private BufferedWriter bufWriter;
	private BufferedReader bufReader;
	
	public TxtLogger(String fileName){
		this.fileName = fileName;
		
		try {
			FileWriter fileWriter = new FileWriter(fileName);		
			FileReader fileReader = new FileReader(fileName);
			
			bufWriter = new BufferedWriter(fileWriter);
			bufReader = new BufferedReader(fileReader);
		} catch (IOException e) {
			System.err.print("Error in conctructor of txtLogger: "+ e.toString());
		}
	}
	
	@Override
	public void addRecord(String record) {
		try {
			bufWriter.write(record+"\n");
			bufWriter.flush();
		} catch (IOException e) {
			System.err.print("Error in addRecord of txtLogger: "+ e.toString());
		}
		
	}

	@Override
	public ArrayList<String> getLogList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLogSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}

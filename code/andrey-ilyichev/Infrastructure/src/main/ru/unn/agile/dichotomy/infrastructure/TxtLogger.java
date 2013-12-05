package ru.unn.agile.dichotomy.infrastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ru.unn.agile.dichotomy.viewmodel.ILogger;

public class TxtLogger implements ILogger {

	private static String fileName;
	private BufferedWriter bufWriter;
	private BufferedReader bufReader;
    private static String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public TxtLogger(String fileName){
		TxtLogger.fileName = fileName;
		
		try {
			FileWriter fileWriter = new FileWriter(fileName);		
			bufWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			System.err.print("Error in conctructor of txtLogger: "+ e.toString());
		}
	}

	
    private static String getCurrentDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(calendar.getTime());
    }
	
	@Override
	public void addRecord(String record) {
		if (record == null){
			throw new IllegalArgumentException("Record was null");
		}
		try {
			bufWriter.write(getCurrentDateAndTime() + " : "+record+"\n");
			bufWriter.flush();
		} catch (IOException e) {
			System.err.print("Error in addRecord of txtLogger: "+ e.toString());
		}
		
	}

	@Override
	public ArrayList<String> getLogList() {
        ArrayList<String> result = new ArrayList<String>();
        try {
			bufReader = new BufferedReader(new FileReader(fileName));
        	String line = bufReader.readLine();
        	
            while (line != null) {

            	result.add(line);
                line = bufReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("error in getLogList : " + e.getMessage());
        }
        return result;
	}

	@Override
	public int getLogSize() {
		int res = getLogList().size();
		return res;
	}
}

package ru.unn.agile.dichotomy.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TxtLoggerTest {
	private String fileName = "testLog.txt";
	private TxtLogger txtLogger;;
	
	@Before
	public void setUp(){
		txtLogger = new TxtLogger(fileName);
	}
	
	@Test
	public void correctCreationTextLoggerWithCorrectFileName(){
		TxtLogger newTxtLogger = new TxtLogger(fileName);
		assertNotNull(newTxtLogger);
	}

	@Test 
	public void correctAddSomeRecords(){
		String testRecord1 = "it's first test record";
		String testRecord2 = "it's second test record";
		
		txtLogger.addRecord(testRecord1);
		txtLogger.addRecord(testRecord2);
		
		ArrayList<String> recordList = getRecordsFromFile();
		String firstRecord = recordList.get(0);
		String lastRecord = recordList.get(1);
		
		System.out.println(firstRecord);
		System.out.println(lastRecord);
		System.out.println(recordList.size());
		
		assertEquals(testRecord1, firstRecord);
		assertEquals(testRecord2,lastRecord);
	}

    @Test 
	public void correctAddSingleRecord(){
		String testRecord = "it's test record";
		txtLogger.addRecord(testRecord);
		

		
		String lastRecord = getRecordsFromFile().get(0);
		
		assertEquals(lastRecord,testRecord);
	}
    
	private ArrayList<String> getRecordsFromFile(){
        BufferedReader bufReader;
        ArrayList<String> result = new ArrayList<String>();
        try {
        	bufReader = new BufferedReader(new FileReader(fileName));
            String line = bufReader.readLine();

            while (line != null) {
            	result.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
	}
}

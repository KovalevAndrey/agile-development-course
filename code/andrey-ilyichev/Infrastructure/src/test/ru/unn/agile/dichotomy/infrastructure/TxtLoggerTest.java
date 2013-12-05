package ru.unn.agile.dichotomy.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class TxtLoggerTest {
	private String fileName = "testLog.txt";
	private TxtLogger txtLogger;
	private final String patternOfRecordWithDate = "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}.*";
	
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

	private void assertEqualsWithRegularExpr(String expression, String testString){
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(testString);
        assertEquals(true, matcher.matches());
    }
	
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
	public void correctAddSingleRecord(){
		String testRecord = "it's test record";
		txtLogger.addRecord(testRecord);
		
		String lastRecord = getRecordsFromFile().get(0);
		
		assertEqualsWithRegularExpr(".*" + testRecord + "$",lastRecord);
	}	
    
	@Test 
	public void correctAddSomeRecord(){
		String testRecord1 = "it's first test record";
		String testRecord2 = "it's second test record";
		
		txtLogger.addRecord(testRecord1);
		txtLogger.addRecord(testRecord2);
		
		ArrayList<String> recordList = getRecordsFromFile();
		String firstRecord = recordList.get(0);
		String lastRecord = recordList.get(1);
		
		assertEqualsWithRegularExpr(".*" + testRecord1 + "$",firstRecord);
		assertEqualsWithRegularExpr(".*" + testRecord2 + "$",lastRecord);
	}

	@Test
	public void addRecordThrowsExceptionIfRecordIsNull(){
		try{
			txtLogger.addRecord(null);
			fail("Exception was expected");
		} catch(Exception ex) {
			assertEquals("Record was null", ex.getMessage());
		}
	}
	
	@Test
	public void anyRecordHasDate(){
		String testRecord1 = "it's first test record with date";
		String testRecord2 = "it's second test record with date";
		
		txtLogger.addRecord(testRecord1);
		txtLogger.addRecord(testRecord2);
		
		ArrayList<String> recordList = getRecordsFromFile();
		String firstRecord = recordList.get(0);
		String lastRecord = recordList.get(1);
		
		assertEqualsWithRegularExpr(patternOfRecordWithDate + " : "+testRecord1+"$",firstRecord);
		assertEqualsWithRegularExpr(patternOfRecordWithDate + " : "+testRecord2+"$",lastRecord);
	}
	
	@Test
	public void correctGettingLogSize(){
		String testRecord1 = "it's first test record";
		String testRecord2 = "it's second test record";
		
		txtLogger.addRecord(testRecord1);
		txtLogger.addRecord(testRecord2);
		
		int recordCount = getRecordsFromFile().size();
		int logSize = txtLogger.getLogSize();
		
		assertEquals(recordCount, logSize);
	}	
}

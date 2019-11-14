package com.mebank.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.text.ParseException;

import com.mebank.accountoperation.AccountBalanceCalculator;

/**
 * Unit test for simple App.
 */
public class AppTest2 
    extends TestCase
{
	String transactionsCSV = "";
	AccountBalanceCalculator accountBalanceCalculator = new AccountBalanceCalculator();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest2( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest2.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	String testString="Relative balance for the period is: -$7.25\nNumber of transactions included is: 1";
    	String accountId= "ACC334455";
    	String fromTime="20/10/2018 19:40:00";
    	String toTime="21/10/2018 09:31:00";
    	String csvFile="./transactions.csv";
    	accountBalanceCalculator.setCSV(csvFile);
    	String result;
		try {
			result = accountBalanceCalculator.calculateBalance(accountId, fromTime, toTime );
			System.out.println(result);
			if (testString.equals(result)){
	    		assertTrue( true );
	    	}else{
	    		assertTrue( false );
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue( false );
		}
    	
    }
    
    
    
}

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
public class AppTest4 
    extends TestCase
{
	String transactionsCSV = "";
	AccountBalanceCalculator accountBalanceCalculator = new AccountBalanceCalculator();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest4( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest4.class );
    }

    /**
     * Rigourous Test :-)
     * No Account ID
     */
    public void testApp()
    {
    	String testString="Relative balance for the period is: $0.00\nNumber of transactions included is: 0";
    	String accountId= "ACC334455X";
    	String fromTime="20/10/2018 12:00:00";
    	String toTime="20/10/2018 19:00:00";
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

package com.mebank.accountoperation;


import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVReader;

public class AccountBalanceCalculator {

	String transactionCSV;

	public String calculateBalance(String accountId, String fromTime, String toTime) throws IOException, ParseException {
	
		CSVReader csvReader=null;
		try{
			
			//Read CSV file 
			csvReader = new CSVReader(new FileReader(transactionCSV));

			String[] record;
			float balance=0;
			int transactions=0;
			Date fromDateTime= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(fromTime);  
			Date toDateTime= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(toTime);
			Set<String> set = new HashSet<String>();

			while ((record = csvReader.readNext()) != null) {

				//Parse data from CSV
				Date transctTime=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(record[3]);
				String transcId=record[0]; 
				String fromAccount=record[1];
				String toAccount=record[2];
				float amount=Float.parseFloat(record[4]);
				String transactionType=record[5];
				String refTransId="";
				if(record.length>6){
					 refTransId=record[6];
				}

				
				//Check if Transactions is eligible for processing
				if(transctTime.before(toDateTime)
						&&transctTime.after(fromDateTime)
						&&"PAYMENT".equals(transactionType)
						&&(accountId.equals(fromAccount)||accountId.equals(toAccount))){

					//Populate the Transaction for Reversal check
					set.add(transcId);

					//Update Balance
					transactions++;
					if(accountId.equals(fromAccount)){
						balance-=amount;
					}else if((accountId.equals(toAccount)&&"PAYMENT".equals(transactionType))){
						balance+=amount;
					}



				}else if("REVERSAL".equals(transactionType)&&set.contains(refTransId)
						&&transctTime.after(fromDateTime)){ //Check for REVERSAL
					//Reverse the transaction if it was processed
					if(accountId.equals(fromAccount)){
						transactions--;
						if(accountId.equals(fromAccount)){
							balance+=amount;
						}else if(accountId.equals(toAccount)){
							balance-=amount;
						}
					}

				}
			}
			
			//Format Balance  in Currency Format
			String balString="$"+String.format("%,.2f", Math.abs(balance));
			if(balance<0){
				balString="-"+balString;
			}
			return "Relative balance for the period is: "+balString+"\nNumber of transactions included is: "+transactions;
		}finally{
			if(csvReader!=null){
				csvReader.close();
			}
		}
	}

	public void setCSV(String csvFile) {
		// TODO Auto-generated method stub
		transactionCSV=csvFile;
	}

}

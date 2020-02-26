package edu.shawnhamilton.advancedjava;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.shawnhamilton.advancedjava.StockQuote;

public class BasicStockService implements StockService {
	public enum IntervalEnum {
		HOURLY, TWELVE_HOURS, DAILY, MONTHLY;
	}
	
    public BasicStockService() {}
    
    public StockQuote getQuote(String tickerSymbol) {
    	
    	// Calendar testDate - hardwired variable used for testing first getQuote() method. 
    	// It returns an instance of the current date and time.
    	Calendar testDate = Calendar.getInstance();
    	
        // Handles test from StockServiceDemo for first getQuote() method
        StockQuote sq = new StockQuote();
        if (tickerSymbol.contentEquals("APPL")) {
        	sq = new StockQuote(125.25, "APPL", testDate);
        }
        return sq;   	
    }
    
    public List<StockQuote> getQuote(String tickerSymbol, Calendar fromDate, Calendar endDate, IntervalEnum interval) {
    	
    	// An array list to store the values retrieved from the source
    	List<StockQuote> stocksRetrieved = new ArrayList<>();
    	
    	// For now, 4 different functions that create Array Lists (one for each interval)
    	// are used to populate store, and retrieve the stock quote data based on the user's
    	// choice of the interval, storing the data in the quotesListTemp variable.
    	List<StockQuote> quotesTempList = new ArrayList<>();
    	
    	if (interval == BasicStockService.IntervalEnum.HOURLY) {
            quotesTempList = createHourlyObjects();
    	}
    	else if( interval == BasicStockService.IntervalEnum.TWELVE_HOURS) {
            quotesTempList = createTwelveHourObjects();
    	}
    	else if( interval == BasicStockService.IntervalEnum.DAILY) {
            quotesTempList = createDailyObjects();
    	}
    	else if( interval == BasicStockService.IntervalEnum.MONTHLY) {
            quotesTempList = createMonthlyObjects();
    	}
        
    	// An enhanced for loop that compares the tickerSymbol, fromDate, and endDate 
    	// variable values passed into our second getQuote() method, with the tickerSymbol
    	// and date values for a given stock in the list. If the tickerSymbol cannot be 
    	// found, the loop exits and a message is printed letting the user know.
    	for (StockQuote quote : quotesTempList) {
    		if ( ( quote.getTickerSymbol().equals(tickerSymbol) ) == false) {
    			System.out.println("The system cannot locate the stock by the tickerSymbol");
    			break;
    		}
    		// The line of code below is a fancy way of saying "If the date of the stock is
    		// equal to or greater than the supplied fromDate, and the date of the stock is 
    		// equal to or less than the supplied endDate, then add it to the new array list.
    		if ( ( quote.getDate().equals(fromDate) || quote.getDate().after(fromDate) ) &&
    				( quote.getDate().equals(endDate) || quote.getDate().before(endDate) ) ) {
    		    stocksRetrieved.add(quote);
    		}  		
    	}
        return stocksRetrieved;
    } 
    
    // Below are 4 private helper methods that serve as our data sources. They each create
    // a year's worth of Apple stock quotes at arbitrary prices (for now) at 4 different intervals;
    // hourly, every 12 hours, daily, and monthly. The actual single data source chosen is specified
    // with the help of the EnumInterval above.
    
    private List<StockQuote> createMonthlyObjects() { 
    	
    	List<StockQuote> monthlyStockHistory = new ArrayList<>(); 
    	
    	Calendar startDate = new GregorianCalendar();
    	Calendar endDate = new GregorianCalendar();
    	Calendar monthlyDate = new GregorianCalendar();
    	
    	double price = 100.34;
    	
    	startDate.set(2020, 0, 15);
    	endDate.set(2020, 11, 15);
    	
    	while (startDate.before(endDate) || startDate.equals(endDate)) {
    		// Variable that will be used to hold and populate the day values for a year of stocks.
    		monthlyDate = (Calendar) startDate.clone(); 
    	    // Add the stock to the list.
    		monthlyStockHistory.add(new StockQuote(price, "APPL", monthlyDate)); 
    	    // Increment the month by one.
    	    startDate.add(Calendar.MONTH, 1);
            //Increment the price
    	    price = price + 50/1+2;
    	 
    	}
    
    	return monthlyStockHistory;
    }
    
    private List<StockQuote> createTwelveHourObjects() { 
    	
    	List<StockQuote> twelveHourStockHistory = new ArrayList<>(); 
    	
    	Calendar startDate = new GregorianCalendar();
    	Calendar endDate = new GregorianCalendar();
    	Calendar twelveHourlyDate = new GregorianCalendar();
    	
    	double price = 100.34;
    	
    	startDate.set(2020, 0, 1);
    	endDate.set(2020, 11, 31);
    	
    	while (startDate.before(endDate) || startDate.equals(endDate)) {
    		// Variable that will be used to hold and populate the bi-daily values for a year of stocks.
    		twelveHourlyDate = (Calendar) startDate.clone(); 
    	    // Add the stock to the list.
    		twelveHourStockHistory.add(new StockQuote(price, "APPL", twelveHourlyDate)); 
    	    // Increment the hour by twelve.
    	    startDate.add(Calendar.HOUR_OF_DAY, 12);
            //Increment the price
    	    price = price + 2;
    	 
    	    }

    	    return twelveHourStockHistory;
        }
    
    private List<StockQuote> createDailyObjects() { 
    	
    	List<StockQuote> dailyStockHistory = new ArrayList<>(); 
    	
    	Calendar startDate = new GregorianCalendar();
    	Calendar endDate = new GregorianCalendar();
    	Calendar dailyDate = new GregorianCalendar();
    	
    	double price = 100.34;
    	
    	startDate.set(2020, 0, 1);
    	endDate.set(2020, 11, 31);
    	
    	while (startDate.before(endDate) || startDate.equals(endDate)) {
    		// Variable that will be used to hold and populate the day values for a year of stocks.
    		dailyDate = (Calendar) startDate.clone(); 
    	    // Add the stock to the list.
    		dailyStockHistory.add(new StockQuote(price, "APPL", dailyDate)); 
    	    // Increment the day by one.
    	    startDate.add(Calendar.DATE, 1);
            //Increment the price
    	    price = price + 4;
    	 
    	}
    
    	return dailyStockHistory;
    }
    
private List<StockQuote> createHourlyObjects() { 
    	
    	List<StockQuote> hourlyStockHistory = new ArrayList<>(); 
    	
    	Calendar startDate = new GregorianCalendar();
    	Calendar endDate = new GregorianCalendar();
    	Calendar hourlyDate = new GregorianCalendar();
    	
    	double price = 100.34;
    	
    	startDate.set(2020, 0, 1);
    	endDate.set(2020, 11, 31);
    	
    	while (startDate.before(endDate) || startDate.equals(endDate)) {
    		// Variable that will be used to hold and populate the hour values for a year of stocks.
    		hourlyDate = (Calendar) startDate.clone(); 
    	    // Add the stock to the list.
    		hourlyStockHistory.add(new StockQuote(price, "APPL", hourlyDate)); 
    	    // Increment the hour by one.
    	    startDate.add(Calendar.HOUR_OF_DAY, 1);
            //Increment the price
    	    price = price + .2;
    	 
    	}  
    	return hourlyStockHistory;
    }

}


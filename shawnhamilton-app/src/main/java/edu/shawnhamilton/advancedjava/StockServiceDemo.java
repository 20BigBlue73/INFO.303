package edu.shawnhamilton.advancedjava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.shawnhamilton.advancedjava.BasicStockService.IntervalEnum;

/*
 *  --A class to test our week 5 StockServiceFactory implementation--
 *  
 *  The object stockservice, is instantiated from our StockService interface
 *  using our StockServiceFactory method getStockService(), which 
 *  returns an instance of a BasicStockService. Its field values are 
 *  then printed through the getQuote() method of the stock service object.
 *  
 *  Week 4 - A second (overloadable) getQuote() method has been added to our
 *  implementation, so we invoke it below to test it out.
 *  
 *  Week 5 - An Enumerated Type has been added to allow the user to specify an interval 
 *  (HOURLY, TWELVE_HOURS, DAILY, and MONTHLY), for viewing stocks in the date range chosen.
 */

public class StockServiceDemo {
	
	public static void main( String[] args ) throws ParseException {
        
		// Create object for demo class testing for first getQuote() method.
        StockService stockservice1 = StockServiceFactory.getStockService();
        
        // Create object for demo class testing for second getQuote() method.
        StockService stockservice2 = StockServiceFactory.getStockService();
        
        // Create object for viewable output (below).  
        StockService stockservice3 = StockServiceFactory.getStockService();
        
        // Create object for viewable output (below).  
        StockService stockservice4 = StockServiceFactory.getStockService();
        
        // Create object for viewable output (below).  
        StockService stockservice5 = StockServiceFactory.getStockService();
        
        // Create object for viewable output (below).  
        StockService stockservice6 = StockServiceFactory.getStockService();
        
        // Test for the first getQuote() method.
        System.out.println(stockservice1.getQuote("APPL"));
        
        // An object to parse date Strings that can
        // be converted to Calender objects
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");    
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy/HH");
        
        // Create Calendar objects for a date range of stocks we wish to retrieve.
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        
        // Parse String dates to our Calendar objects to specify date range
        startDate.setTime(sdf.parse("10/1/2020"));
        endDate.setTime(sdf.parse("11/1/2020"));
        
        // A test for our second getQuote() method. Prints an ArrayList of hourly,
        // twelve hourly, daily, or monthly stock quotes within the given date range.
        System.out.println(stockservice2.getQuote("APPL", startDate, endDate, IntervalEnum.DAILY ));
        
        // Yet another way to retrieve data from an object. Made easy by Professor Kramer!
        // Also demonstrate quotes displayed by the hour in date range specified.
        List<StockQuote> hourlyHistory = stockservice3.getQuote("APPL", startDate, endDate, IntervalEnum.HOURLY);
        System.out.print( "Hourly Quotes:\n" );
        for (StockQuote singleDay : hourlyHistory) {
			System.out.printf("%s %s %6.2f \n", singleDay.getTickerSymbol(), 
					singleDay.getDate().getTime(), singleDay.getValue());
		}
        System.out.print( "\n\n\n" );
        
        // Demonstrate quotes displayed by the twelve hour interval in date range specified.
        List<StockQuote> twelveHourHistory = stockservice4.getQuote("APPL", startDate, endDate, IntervalEnum.TWELVE_HOURS);
        System.out.print( "Quotes Every Twelve Hours:\n" );
        for (StockQuote singleDay : twelveHourHistory) {
			System.out.printf("%s %s %6.2f \n", singleDay.getTickerSymbol(), 
					singleDay.getDate().getTime(), singleDay.getValue());
		}
        System.out.print( "\n\n\n" );
        
        // Demonstrate quotes displayed by the day in date range specified.
        List<StockQuote> dailyHistory = stockservice5.getQuote("APPL", startDate, endDate, IntervalEnum.DAILY);
        System.out.print( "Daily Quotes:\n" );
        for (StockQuote singleDay : dailyHistory) {
			System.out.printf("%s %s %6.2f \n", singleDay.getTickerSymbol(), 
					singleDay.getDate().getTime(), singleDay.getValue());
		}
        System.out.print( "\n\n\n" );
        
        // Demonstrate quotes displayed by the month in date range specified.
        List<StockQuote> monthlyHistory = stockservice5.getQuote("APPL", startDate, endDate, IntervalEnum.MONTHLY);
        System.out.print( "Monthly Quotes:\n" );
        for (StockQuote singleDay : monthlyHistory) {
			System.out.printf("%s %s %6.2f \n", singleDay.getTickerSymbol(), 
					singleDay.getDate().getTime(), singleDay.getValue());
		}
    }
}

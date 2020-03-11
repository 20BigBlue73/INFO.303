package edu.shawnhamilton.advancedjava;

import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.shawnhamilton.advancedjava.BasicStockService.IntervalEnum;

/*
 *  --A class to test our week 6 StockServiceFactory implementation--
 *  
 *  The object stockservice, is instantiated from our StockService interface
 *  using our StockServiceFactory method getStockService(), which 
 *  returns an instance of a DatabaseStockService. Its field values are 
 *  then printed through the getQuote() method of the stock service object.
 *  
 *  Week 4 - A second (overloadable) getQuote() method has been added to our
 *  implementation, so we invoke it below to test it out.
 *  
 *  Week 5 - An Enumerated Type has been added to allow the user to specify an interval 
 *  (HOURLY, TWELVE_HOURS, DAILY, and MONTHLY), for viewing stocks in the date range chosen.
 *  
 *  Week 6 - StockServiceDemo has been updated to work with an sql database. In theory,
 *  it is designed to work using the IntervalEnum so that there is a single daily quote 
 *  for each stock instance (record) in the db. The program now reflects the implementation 
 *  of the DatabaseStockService instead of the BasicStockService, but still makes use of the
 *  IntervalEnum in that class --- hmmm maybe it should be a top level enum class at this 
 *  point.
 */

public class StockServiceDemo {
    
    public static void main( String[] args ) throws ParseException, StockServiceException {
        
        // Create object for demo class testing for first getQuote() method.
        StockService stockservice1 = StockServiceFactory.getStockService();
        
        // Create object for demo class testing for user input and first getQuote() method.
        StockService stockservice2 = StockServiceFactory.getStockService();
        
        // Create object for demo class testing for second getQuote() method.
        StockService stockservice3 = StockServiceFactory.getStockService();
        
        // Create object for demo class testing for second getQuote() method.
        StockService stockservice4 = StockServiceFactory.getStockService();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter a valid 4 letter stock symbol to lookup its most recent details: ");
        
        // String input 
        String symbol = sc.next();     
        
        System.out.println("The latest information is: \n" + stockservice2.getQuote(symbol));      
        
        // Two objects to parse date Strings that can
        // be converted to Calendar objects
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");    
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        
        // Create Calendar objects for a date range of stocks we wish to retrieve.
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        
        System.out.println("Please enter the stock symbol, a start date, an end date, and an interval.");
        System.out.println("Each must be separated by a single space. Then hit enter button.");
        
        // String input 
        String inputSymbol = sc.next();
        
        // String input 
        String inputStartDate = sc.next();
        
        // String input 
        String inputEndDate = sc.next();
        
        // String input 
        String userInterval = sc.next();
        
        // Parse String input dates to our Calendar objects to specify date range
        startDate.setTime(sdf.parse(inputStartDate));
        endDate.setTime(sdf.parse(inputEndDate));
        
        List<StockQuote> history = stockservice4.getQuote(inputSymbol, startDate, endDate, userInterval);
        System.out.print( "Quotes:\n" );
        for (StockQuote singleDay : history) {
            System.out.printf("%s %s %6.2f \n", singleDay.getTickerSymbol(), 
                    singleDay.getDate().getTime(), singleDay.getValue());
        }
    }
}

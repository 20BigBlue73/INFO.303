package edu.shawnhamilton.advancedjava;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import edu.shawnhamilton.advancedjava.BasicStockService.IntervalEnum;

import org.junit.Test;

import edu.shawnhamilton.advancedjava.StockQuote;

public class StockServiceFactoryTest {

	@Test
	public void testGetStockService() {
		
		// Creates a variable for testing to see if our implementation returns an instance
		// of the BasicStockService class, and also gives us a reference to the getQuote()
		// method we can test in the same way.
		StockService stockservice = StockServiceFactory.getStockService();
		
		// This variable will let us test our values that are created when invoking our no 
		// argument default constructor, which is the same method invoked in the current
		// implementation of the StockServiceFactory.getStockService() method above when it 
		// returns a reference to the BasicStockService class.
		StockQuote stockquote = new StockQuote();
		
		// Test that StockServiceFactory.getStockService() method returns an instance
		// of the BasicStockService class that implements our StockService interface.
		assertTrue(stockservice instanceof BasicStockService);
		
		// Test to make sure our getQuote method returns an instance of a StockQuote class.
		assertTrue(stockservice.getQuote("") instanceof StockQuote);
		
		// Test to make sure our getQuote method returns an instance of a List.
		Calendar testDate1 = new GregorianCalendar(2020, 3, 15);
		Calendar testDate2 = new GregorianCalendar(2020, 8, 15);
		assertTrue(stockservice.getQuote("", testDate1, testDate2, IntervalEnum.HOURLY) instanceof List);
		
		// Test to make sure an instance of the default no argument constructor provides
		// the correct values (also tested in StockQuote class).
		Calendar testDate3 = new GregorianCalendar(2020, 1, 15);
		assertEquals("The value is correct", stockquote.getValue(), 0, 0.2);
		assertEquals("The tickerSymbol is correct", stockquote.getTickerSymbol(), "UNDEFINED");
		assertEquals("The date is correct", stockquote.getDate(), testDate3);
	}

}

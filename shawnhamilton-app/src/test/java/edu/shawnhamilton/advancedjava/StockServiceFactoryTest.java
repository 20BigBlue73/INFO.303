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
	public void testGetStockService() throws StockServiceException {
		
		// Creates a variable for testing to see if our implementation returns an instance
		// of the BasicStockService class, and also gives us a reference to the getQuote()
		// method we can test in the same way.
		StockService stockservice = StockServiceFactory.getStockService();
		
		// Test that StockServiceFactory.getStockService() method returns an instance
		// of the BasicStockService class that implements our StockService interface.
		assertTrue(stockservice instanceof DatabaseStockService);
		
		// Test to make sure our getQuote method returns an instance of a StockQuote class.
		//assertTrue(stockservice.getQuote("") instanceof StockQuote);
		
		
	}

}

package edu.shawnhamilton.advancedjava;
/*
 * A JUnit test class with a couple of test methods for the constructors 
 */
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import edu.shawnhamilton.advancedjava.StockQuote;

public class StockQuoteTest {

	private double value;
	private String tickerSymbol;
	private Calendar date;
	
	@Before
    public void setup() {
		
		value = 125.25;
		tickerSymbol = "GOOG";
		date = Calendar.getInstance();
	}
	
	@Test
    public void noargConstructorTest() {
        StockQuote stockquote = new StockQuote();
        Calendar testDate = new GregorianCalendar(2020, 1, 15);
        assertEquals("Empty value", stockquote.getValue(), 0, 0.2);
        assertEquals("Undefined tickerSymbol", stockquote.getTickerSymbol(), "UNDEFINED");
        assertEquals("Default Date", stockquote.getDate(), testDate);
    }
	
	@Test
    public void testStockQuoteConstruction() {
		
		StockQuote stockquote = new StockQuote(value, tickerSymbol, date);
		
	    assertEquals("The value is correct", stockquote.getValue(), value, 0.2);
		assertEquals("The tickerSymbol is correct", stockquote.getTickerSymbol(), tickerSymbol);
		assertEquals("The date is correct", stockquote.getDate(), date);
	}

}

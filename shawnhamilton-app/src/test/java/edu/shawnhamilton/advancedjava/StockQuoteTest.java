package edu.shawnhamilton.advancedjava;
/*
 * A JUnit test class with a couple of test methods for the constructors 
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.shawnhamilton.advancedjava.StockQuote;

public class StockQuoteTest {

	private double value;
	private String tickerSymbol;
	
	@Before
    public void setup() {
		
		value = 125.25;
		tickerSymbol = "GOOG";
	}
	
	@Test
    public void noargConstructorTest() {
        StockQuote stockquote = new StockQuote();
        assertEquals("Empty value", stockquote.getValue(), 0, 0.2);
        assertEquals("Undefined tickerSymbol", stockquote.getTickerSymbol(), "UNDEFINED");
    }
	
	@Test
    public void testStockQuoteConstruction() {
		
		StockQuote stockquote = new StockQuote(value, tickerSymbol);
		
	    assertEquals("The value is correct", stockquote.getValue(), value, 0.2);
		assertEquals("The tickerSymbol is correct", stockquote.getTickerSymbol(), tickerSymbol);
	}

}

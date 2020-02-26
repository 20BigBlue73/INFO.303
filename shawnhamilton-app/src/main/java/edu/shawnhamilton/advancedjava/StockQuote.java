package edu.shawnhamilton.advancedjava;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

/*
 * A container class for stock quotes
 */
public class StockQuote {

	private double value;
	private String tickerSymbol = "UNDEFINED";
	Calendar date = new GregorianCalendar(2020, 1, 15);
	
	// A couple of empty optional variables created from the Optional class.
	// No need to worry about null pointer exceptions from these guys.
	// I must admit that I have not implemented them in the rest of the project yet,
	// due to the fact that the class itself changes, breaking existing compatibility
	// with the rest of the code.
	//private Optional<String> tickerSymbolOptional = Optional.empty();
	//private Optional<Calendar> dateOptional = Optional.empty();
	
	// No argument default constructor
	public StockQuote () {}
	
	public StockQuote (double value, String tickerSymbol, Calendar date) {
		this.value = value;
		this.tickerSymbol = tickerSymbol;
		this.date = date;
	}
	
	public StockQuote (StockQuote stockQuote) {
		this.value = stockQuote.value;
		this.tickerSymbol = stockQuote.tickerSymbol;
		this.date = stockQuote.date;
	}
	
	public double getValue() {
		return value;
	}
	
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	
	public Calendar getDate() {
		return date;
	}
	
		// Override toString() method for output and debugging
	@Override public String toString() {
		return "StockQuote-  value: " + String.format("%.2f", value) + 
				"  tickerSymbol: " + tickerSymbol + "  date: " + 
				date.getTime() + "\n";
	}
}

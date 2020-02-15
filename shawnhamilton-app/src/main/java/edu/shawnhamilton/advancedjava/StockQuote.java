package edu.shawnhamilton.advancedjava;
/*
 * A container class for stock quotes
 */
public class StockQuote {

	private double value = 0;
	private String tickerSymbol = "UNDEFINED";
	
	// No argument default constructor
	public StockQuote () {}
	
	public StockQuote (double value, String tickerSymbol) {
		this.value = value;
		this.tickerSymbol = tickerSymbol;
	}
	
	public StockQuote (StockQuote stockQuote) {
		this.value = stockQuote.value;
		this.tickerSymbol = stockQuote.tickerSymbol;
	}
	
	public double getValue() {
		return value;
	}
	
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	
	// Override toString() method for output and debugging
	@Override public String toString() {
		return "StockQuote: value=" + value + " tickerSymbol=" + tickerSymbol;
	}
}

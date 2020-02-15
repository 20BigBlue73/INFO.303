package edu.shawnhamilton.advancedjava;

import edu.shawnhamilton.advancedjava.StockQuote;

public class BasicStockService implements StockService {
	
    public BasicStockService() {}
    
    public StockQuote getQuote(String tickerSymbol) {
    	
    	// Hard coded for now - returns default no argument constructor values
        return new StockQuote();   	     	
    }
}

package edu.shawnhamilton.advancedjava;

/*
 *  --A class to test our week 3 StockServiceFactory implementation--
 *  
 *  The object stockservice, is instantiated from our StockService interface
 *  using our StockServiceFactory method getStockService(), which 
 *  returns an instance of a BasicStockService. Its field values are 
 *  then printed through the getQuote() method of the stock service object.
 */

public class StockServiceDemo {
	
	public static void main( String[] args ) {
        
		// Create object for demo class testing
        StockService stockservice = StockServiceFactory.getStockService();
        
        // Notice empty String for getQuote() parameter, as values are hard coded for now
        // in BasicStockService class.
        System.out.println(stockservice.getQuote(""));        
        
    }
}

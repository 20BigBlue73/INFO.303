package edu.shawnhamilton.advancedjava;

/*
 * returns a concrete implementation of the StockService Interface 
 */

public class StockServiceFactory {
	
    public static StockService getStockService() {
    	
    	return new DatabaseStockService();
    }
    
}

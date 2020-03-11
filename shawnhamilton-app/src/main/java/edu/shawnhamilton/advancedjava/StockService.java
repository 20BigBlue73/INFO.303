package edu.shawnhamilton.advancedjava;

import java.util.Calendar;
import java.util.List;

import edu.shawnhamilton.advancedjava.BasicStockService.IntervalEnum;

public interface StockService {
	/**
	 * Return the current price for a share of stock for the given symbol
	 * 
	 * @param symbol 
	 *         the stock symbol of the company you want a quote for. e.g. APPL
	 *         for APPLE
	 *
	 * @return a <CODE>StockQuote</CODE> instance
	 */
	StockQuote getQuote(String symbol) throws StockServiceException;
	
	/**
	 * Get a historical list of stock quotes for the provided stock symbol
	 * 
	 * @param symbol
	 *        stock symbol to search for
	 * @param startDate
	 *        start date
	 * @param endDate
	 *        end date (inclusive)
	 * 
	 * @return 
	 *      a list of StockQuote instances, one for each day in the range specified. 
	 */
	List<StockQuote> getQuote(String symbol, Calendar startDate, Calendar endDate, String interval);
	
}

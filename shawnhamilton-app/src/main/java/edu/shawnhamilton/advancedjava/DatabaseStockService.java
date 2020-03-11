package edu.shawnhamilton.advancedjava;

import edu.shawnhamilton.advancedjava.BasicStockService.IntervalEnum;
import edu.shawnhamilton.advancedjava.StockServiceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.annotation.Immutable;
import javax.validation.constraints.NotNull;

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */
public class DatabaseStockService implements StockService {

    /**
     * Return the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    @Override
    public StockQuote getQuote(String symbol) throws StockServiceException {
        // todo - this is a pretty lame implementation why?
    	
    	// This has since been revised to return the most recent stock quote available 
    	// based on the symbol. Still it should probably not be using an ArrayList,
    	// as it only returns a single StockQuote instance.
        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol + "' order by time desc limit 1";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while(resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                // oops!
                double price = resultSet.getDouble("price");
                stockQuotes.add(new StockQuote(price, symbolValue, calendar));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }
        return stockQuotes.get(0);
    }

    /**
     * Get a historical list of stock quotes for the provide symbol
     *
     * @param symbol the stock symbol to search for
     * @param start   the date of the first stock quote
     * @param end  the date of the last stock quote
     * @param interval cadence
     * @return a list of StockQuote instances
     */
    @Override
    public List<StockQuote> getQuote(@NotNull String symbol, @NotNull Calendar start, @NotNull Calendar end, @NotNull String interval) {
    	
    	// workaround - add an extra day to end parameter so date range results are inclusive 
    	end.add(Calendar.DATE, 1);
    	
    	// convert Calendar dates to sql Dates for database query
    	Date startDate = new Date(start.getTimeInMillis());
    	Date endDate = new Date(end.getTimeInMillis());
    	
    	// return integer for use with .relative method, based on user's choice of interval
    	int queryinterval = IntervalEnum.queryInterval(interval);
    	
    	// Returns an ArrayList of quotes matching the date range specified
    	// from the stocks sql database
    	List<StockQuote> stockQuotes2 = new ArrayList<>();
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol + "' and time >= '" + 
                startDate + "' and time <= '" + endDate + "' order by time";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes2 = new ArrayList<>(resultSet.getFetchSize());
            while(resultSet.relative(queryinterval)) {
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                // workaround for off by 1 day error I experienced with sql date conversion
                calendar.add(Calendar.DATE, 1);
                // oops!
                double price = resultSet.getDouble("price");
                stockQuotes2.add(new StockQuote(price, symbolValue, calendar));
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            System.out.println("A Database Exception Has Occurred");
        }
        if (stockQuotes2.isEmpty()) {
            System.out.println("There is no stock data for: " + symbol);
        }
        return stockQuotes2;
    }
}

package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class DB {

	Connection conn;

	public DB() {}

	public void dbConnect(String dbName, String username, String pw)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection(dbName);
	        System.out.println("connected");
	        }
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String prepareDate(Date d){

		Calendar c = Calendar.getInstance();
		c.setTime(d);

		String s = "";

		// Get year
		s = Integer.toString(c.get(c.YEAR)) + "-";

		// Get month
		if (c.get(c.MONTH) < 10)
			s = s + "0" + Integer.toString(c.get(c.MONTH)+1) + "-";
		else
			s = s + Integer.toString(c.get(c.MONTH)+1) + "-";

		// Get Day
		if (c.get(c.DAY_OF_MONTH) < 10)
			s = s + "0" + Integer.toString(c.get(c.DAY_OF_MONTH)) + " ";
		else
			s = s + Integer.toString(c.get(c.DAY_OF_MONTH)) + " ";

		// Get Hour
		if (c.get(c.HOUR_OF_DAY) < 10)
			s = s + "0" + Integer.toString(c.get(c.HOUR_OF_DAY)) + ":";
		else
			s = s + Integer.toString(c.get(c.HOUR_OF_DAY)) + ":";

		// Get Minute
		if (c.get(c.MINUTE) < 10)
			s = s + "0" + Integer.toString(c.get(c.MINUTE)) + ":";
		else
			s = s + Integer.toString(c.get(c.MINUTE)) + ":";

		// Get Second
		if (c.get(c.SECOND) < 10)
			s = s + "0" + Integer.toString(c.get(c.SECOND)) + ".00";
		else
			s = s + Integer.toString(c.get(c.SECOND)) + ".00";

		System.out.println("HERE: " + s);

		return s;
	}

	public void insertData(String username, Date date, String text, String address)
	{
		String dateString = prepareDate(date);

	    Statement stmt;
	    try
	    {
	        stmt = conn.createStatement();
	        
	        text = text.replaceAll("'", "''");
	       // text = text.replaceAll("'", " ");
	        int id = 0;
	        String cmd = "insert into TUTwitter values(NULL, '" + username + "', '" + dateString + "', '" + text + "', '" + address + "');";

	        System.out.println(cmd);
	        stmt.setEscapeProcessing(true);

	        stmt.executeUpdate(cmd);
	        stmt.close();

	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();

	    }
	}	
	
}

package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.Calendar;

import twitter4j.Status;

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
		s = Integer.toString(c.get(c.YEAR)) + "-" + Integer.toString(c.get(c.MONTH)) + "-"
			+ Integer.toString(c.get(c.DAY_OF_MONTH)) + " " + Integer.toString(c.get(c.HOUR_OF_DAY)) + ":"
			+ Integer.toString(c.get(c.MINUTE)) + ":" + Integer.toString(c.get(c.SECOND)) + "."
			+ Integer.toString(c.get(c.MILLISECOND));


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

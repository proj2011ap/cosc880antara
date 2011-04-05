package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import twitter4j.Status;

public class DB {
	
	Connection conn;
	
	public DB() {}
	
	public void dbConnect(String TUUsersDB, String sa, String antarapal)
	{
		try
		{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
	        conn = DriverManager.getConnection(TUUsersDB, sa, antarapal);
	        System.out.println("connected");
	        }
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void insertData(String username, String date, String text, String address)
	{
	    Statement stmt;
	    try
	    {
	        stmt = conn.createStatement();
	        stmt.executeUpdate("insert into dbo.TUTwitter " +
	             "values('username' , 'date' , 'text' , 'address')");
	
	        //stmt.executeUpdate("insert into cust_profile " +
       // "values('name1', 'add1','city1','state1','country1')");
	        stmt.close();
	        conn.close();
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
}

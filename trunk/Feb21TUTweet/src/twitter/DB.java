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
			Class.forName("org.sqlite.JDBC");
	        conn = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite3BrowserDB\\TUUsersDB");
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
	        text = text.replaceAll("'", "''");
	       // text = text.replaceAll("'", " ");
	        String cmd = "insert into dbo.TUTwitter values(NewID(), '" + username + "', '" + date + "', '" + text + "', '" + address + "');";
	        
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

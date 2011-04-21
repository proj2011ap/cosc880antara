package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

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
	

	public void insertData(String username, String date, String text, String address)
	{
	    Statement stmt;
	    try
	    {
	        stmt = conn.createStatement();
	        text = text.replaceAll("'", "''");
	       // text = text.replaceAll("'", " ");
	        int id = 0;
	        String cmd = "insert into TUTwitter values(NULL, '" + username + "', '" + date + "', '" + text + "', '" + address + "');";
	        
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

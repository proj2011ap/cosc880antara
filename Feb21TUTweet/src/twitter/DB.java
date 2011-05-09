package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Date;

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
		
	/*	java.util.Date dj = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(dj.getTime());
	    System.out.println("utilDate:" + dj);
	    System.out.println("sqlDate:" + sqlDate);    
        s.equals(sqlDate); */
		String s =" ";
		s = d.getYear()+ " - "+ d.getMonth() +" - "+ d.getDay() + d.getHours() +" : "+ d.getMinutes()+ 
				" : "+ d.getSeconds();
		System.out.println(s);
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

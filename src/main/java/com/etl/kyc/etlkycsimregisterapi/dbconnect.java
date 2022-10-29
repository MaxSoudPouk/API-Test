package com.etl.kyc.etlkycsimregisterapi;

import java.sql.*;

public class dbconnect {
	
	
	
	Connection con = null;
	public static Connection connectDB()
	{
	    try
	    {
	         
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://172.19.23.26:3306/etl_simcard_kyc_db?useSSL=false",
	            "etlkycapi", "Kyc@etl2022");
	            
	        return con;
	    }
	  
	    catch (SQLException | ClassNotFoundException e)
	    {
	         
	        System.out.println(e);
	 
	        return null;
	    }
	}
	
	
	
	

	
}
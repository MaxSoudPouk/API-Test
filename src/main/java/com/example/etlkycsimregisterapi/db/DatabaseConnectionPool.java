package com.example.etlkycsimregisterapi.db;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;



public class DatabaseConnectionPool {
private String driverName;
private String password;
private String url;
private String user;
private Driver driver1;
private Vector<Connection> freeConnections;
private int maxConn;
private int count;
/**
 * DatabaseConnectionPool constructor.
 * 
 * @param drivername
 * @param conUrl
 * @param conuser
 * @param conpassword
 * @throws SQLException
 */
public DatabaseConnectionPool(String drivername, String conUrl,
		String conuser, String conpassword) throws SQLException {
	freeConnections = new Vector<Connection>();
	driverName = drivername;
	url = conUrl;
	user = conuser;
	password = conpassword; 

	
	
	//print("Driver name:" + driverName);
	try {
		driver1 = (Driver) Class.forName(driverName).newInstance();	
		DriverManager.registerDriver(driver1);
	} catch (Exception _ex) {
		print(" [ Error 1 ] "+ _ex.getMessage());
	}
	count = 0;
	maxConn = 300;
}
/**
 * Method to destroy all connections.
 */
public void destroy() {
	closeAll();
	try {
		DriverManager.deregisterDriver(driver1);
		return;
	} catch (Exception e) {
		print(" [ Error 3 ] "+ e.getMessage());
		return;
	}
}
/**
 * Method to add free connections in to pool.
 * 
 * @param connection
 */
public synchronized void freeConnection(Connection connection) {
	freeConnections.addElement(connection);
	count--;
	notifyAll();
}
/**
 * Method to get connections.
 * 
 * @return Connection
 */
public synchronized Connection getConnection() {
	Connection connection = null;
	if (freeConnections.size() > 0) {
		connection = (Connection) freeConnections.elementAt(0);
		freeConnections.removeElementAt(0);
		try {
			if (connection.isClosed()) {
				connection = getConnection();
			}
		} catch (Exception e) {
			print(e.getMessage());
			connection = getConnection();
		}
		return connection;
	}
	if (count < maxConn) {
		connection = newConnection();
		//print("NEW CONNECTION CREATED");
	}
	if (connection != null) {
		count++;
	}
	return connection;
}
/**
 * Method to close all resources
 */
private synchronized void closeAll() {
	for (Enumeration<Connection> enumeration = freeConnections.elements(); enumeration
			.hasMoreElements();) {
		Connection connection = (Connection) enumeration.nextElement();
		try {
			connection.close();
		} catch (Exception e) {
		}
	}
	freeConnections.removeAllElements();
}

private Connection newConnection() {
	Connection connection = null;
	try {
		connection = DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
		
		return null;
	}
	return connection;
}
private void print(String print) {
	System.out.println(print);
}
}
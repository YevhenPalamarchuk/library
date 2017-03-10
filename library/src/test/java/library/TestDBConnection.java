package library;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import org.junit.Assert;
import org.junit.Test;

import dbutil.DBConnection;

public class TestDBConnection {

	private Connection connection;
	private DBConnection testConnection = new DBConnection();

	@Test
	public void testConnection2DBIsNotNull() {

		try {
			connection = testConnection.Connect2DB();
			//System.out.println(connection);
			assertNotEquals("Connection to the DataBase was null", null, connection);
		
		} catch (SQLSyntaxErrorException e)
		{
			Assert.fail(e.toString());
		}
		 catch (SQLException e) {
			 Assert.fail(e.toString());
		}
		catch (ClassNotFoundException e)
		{
			
			Assert.fail(e.toString());
		}
		catch (Exception e)
		{
			Assert.fail(e.toString());
		}
}
}

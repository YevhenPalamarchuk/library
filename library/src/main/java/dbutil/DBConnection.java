package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnection {
	
	CONNECT;
	
	private Connection connection;
		
	
	/**
	 * Private constructor
	 */
	private DBConnection() {

		// Reading the property file
		DBConfig dbConfig = new DBConfig();

		try {
			// Loading driver's class
			Class.forName(dbConfig.getDriverName());

			// Making connection to the database
			connection = DriverManager.getConnection(dbConfig.getPath2DB(), dbConfig.getLogin(), dbConfig.getPassword());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Getting connection instance
	 * @return Connection
	 */
	public Connection getConnection(){
		return connection;
	}
	
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConnection;
import model.User;

public class LoginDaoImpl implements LoginDao {
	
	private Connection connection;

	public LoginDaoImpl() {
		
				DBConnection dbCon = new DBConnection();
				try {
					connection = dbCon.Connect2DB();

				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			String sql = "SELECT * FROM authentication ORDER BY id";

			myStmt = connection.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String login = myRs.getString("login");
				String pass = myRs.getString("pass");
				long dateReg = myRs.getLong("date_of_reg");

				// create new book object
				User tempUser = new User(id, login, pass, dateReg);

				// add it to the list of books
				users.add(tempUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close JDBC objects
			close(myStmt, myRs);
		}
		return users;
	}

	@Override
	public void addUser(User theUser) {
		
		PreparedStatement myStmt = null;

		try {
			// create sql for insert
			String sql = "INSERT INTO authentication " + "(login, pass, date_of_reg) " + "values (?, ?, ?)";

			myStmt = connection.prepareStatement(sql);

			// set the param values for the User
			myStmt.setString(1, theUser.getUserLogin());
			myStmt.setString(2, theUser.getUserPass());
			myStmt.setLong(3, theUser.getCreationDate());

			// execute sql insert
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			close(myStmt, null);
		}
		
	}
	
	private void close(Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConnection;
import model.Role;
import model.User;

public class SecurityDaoImpl implements SecurityDao {

	private Connection connection;

	public SecurityDaoImpl() {

		DBConnection dbCon = DBConnection.CONNECT;
		connection = dbCon.getConnection();
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			String sql = "SELECT * FROM user ORDER BY id";

			statement = connection.createStatement();

			// execute query
			resultSet = statement.executeQuery(sql);

			// process result set
			while (resultSet.next()) {

				// retrieve data from result set row
				int id = resultSet.getInt("id");
				String userLogin = resultSet.getString("login");
				String userPass = resultSet.getString("pass");
				Role role = adaptRole(resultSet.getString("role"));
				long creationDate = resultSet.getLong("date_of_reg");
				long lastAuthentication = resultSet.getLong("last_authentication");
				String lastSSID = resultSet.getString("last_ssid");

				// create new book object
				User tempUser = new User(id, userLogin, userPass, creationDate, lastAuthentication, lastSSID, role);

				// add it to the list of books
				users.add(tempUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close JDBC objects
			closeStatement(statement);
			closeResultSet(resultSet);
		}
		return users;
	}

	@Override
	public void addUser(String login, String password, String role, long dateOfRegistration) {

		PreparedStatement statement = null;

		try {
			// create sql for insert
			String sql = "INSERT INTO user " + "(login, pass, role, date_of_reg) " + "values (?, ?, ?,?)";

			statement = connection.prepareStatement(sql);

			// set the parameters values for the User
			statement.setString(1, login);
			statement.setString(2, password);
			statement.setString(3, role);
			statement.setLong(4, dateOfRegistration);

			// execute SQL insert
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			closeStatement(statement);
		}

	}

	@Override
	public int doAuthentication(String login, String password, String ssid, long authentificationTime) {
		int result = 0;
		User theUser = null;
		List<User> usersList = getUsers();

		for (int i = 0; i < usersList.size(); i++) {

			theUser = usersList.get(i);

			if (login != "" & theUser.getUserLogin().equals(login)) {

				if (theUser.getUserPass().equals(password)) {

					PreparedStatement statement = null;

					try {
						// create SQL for insert
						String sql = "UPDATE user " + "SET last_authentication=?, last_ssid=? " + "WHERE id=?";

						statement = connection.prepareStatement(sql);

						// set the parameters values for the User
						statement.setLong(1, authentificationTime);
						statement.setString(2, ssid);
						statement.setInt(3, theUser.getId());

						// execute SQL insert
						statement.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						// clean up JDBC objects
						closeStatement(statement);
					}
					// assigning userId
					return theUser.getId();
				} else {
					// password error
					return -1;
				}
			} else {
				// login error
				result = 0;
			}
		}
		return result;
	}

	private Role adaptRole(String role) {

		role.toLowerCase();
		if (role.equals("admin")) {
			return Role.ADMIN;
		} else {
			return Role.USER;
		}
	}

	private void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void closeStatement(Statement statement) {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

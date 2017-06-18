package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBConnection;
import model.Book;

public class LibraryDaoImpl implements LibraryDao {

	private Connection connection;

	public LibraryDaoImpl() {
		// get db connection
		DBConnection dbCon = DBConnection.CONNECT;
		try {
			connection = dbCon.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getBooks() {

		List<Book> books = new ArrayList<>();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// create sql statement
			String sql = "SELECT * FROM db_books ORDER BY id";

			statement = connection.createStatement();

			// execute query
			resultSet = statement.executeQuery(sql);

			// process result set
			while (resultSet.next()) {

				// retrieve data from result set row
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				int year = resultSet.getInt("year");

				// create new book object
				Book tempBook = new Book(id, title, author, publisher, year);

				// add it to the list of books
				books.add(tempBook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close JDBC objects
			closeResultSet(resultSet);
			closeStatement(statement);
		}
		return books;
	}

	public void addBook(Book theBook) {

		PreparedStatement statement = null;

		try {
			// create sql for insert
			String sql = "INSERT INTO db_books " + "(title, author, publisher,year) " + "values (?, ?, ?, ?)";

			statement = connection.prepareStatement(sql);

			// set the param values for the book
			statement.setString(1, theBook.getTitle());
			statement.setString(2, theBook.getAuthor());
			statement.setString(3, theBook.getPublisher());
			statement.setInt(4, theBook.getYear());

			// execute sql insert
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			closeStatement(statement);
		}
	}

	public Book getBook(String theBookId) {

		Book theBook = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int bookId;

		try {
			// convert book id to int
			bookId = Integer.parseInt(theBookId);

			// create sql to get selected book
			String sql = "SELECT * FROM db_books WHERE id=?";

			// create prepared statement
			statement = connection.prepareStatement(sql);

			// set params
			statement.setInt(1, bookId);

			// execute statement
			resultSet = statement.executeQuery();

			// retrieve data from result set row
			if (resultSet.next()) {
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				int year = resultSet.getInt("year");

				// use the bookId during construction
				theBook = new Book(bookId, title, author, publisher, year);
			} else {
				throw new IOException("\n\nCould not find book id: " + bookId + "\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			closeResultSet(resultSet);
			closeStatement(statement);
		}
		return theBook;
	}

	public void updateBook(Book theBook) {

		PreparedStatement statement = null;

		try {
			// create SQL update statement
			String sql = "update db_books " + "set title=?, author=?, publisher=? , year=? " + "where id=?";

			// prepare statement
			statement = connection.prepareStatement(sql);

			// set params
			statement.setString(1, theBook.getTitle());
			statement.setString(2, theBook.getAuthor());
			statement.setString(3, theBook.getPublisher());
			statement.setInt(4, theBook.getYear());
			statement.setInt(5, theBook.getId());

			// execute SQL statement
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			closeStatement(statement);
		}
	}

	public void deleteBook(String theBookId) {

		PreparedStatement statement = null;

		try {
			// convert book id to int
			int bookId = Integer.parseInt(theBookId);

			// create sql to delete book
			String sql = "delete from db_books where id=?";

			// prepare statement
			statement = connection.prepareStatement(sql);

			// set params
			statement.setInt(1, bookId);

			// execute sql statement
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC code
			closeStatement(statement);
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

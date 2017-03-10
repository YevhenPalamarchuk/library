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
		DBConnection dbCon = new DBConnection();
		try {
			connection = dbCon.Connect2DB();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getBooks() {

		List<Book> books = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// create sql statement
			String sql = "SELECT * FROM db_books ORDER BY id";

			myStmt = connection.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String author = myRs.getString("author");
				String publisher = myRs.getString("publisher");
				int year = myRs.getInt("year");

				// create new book object
				Book tempBook = new Book(id, title, author, publisher, year);

				// add it to the list of books
				books.add(tempBook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close JDBC objects
			close(myStmt, myRs);
		}
		return books;
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

	public void addBook(Book theBook) {

		PreparedStatement myStmt = null;

		try {
			// create sql for insert
			String sql = "INSERT INTO db_books " + "(title, author, publisher,year) " + "values (?, ?, ?, ?)";

			myStmt = connection.prepareStatement(sql);

			// set the param values for the book
			myStmt.setString(1, theBook.getTitle());
			myStmt.setString(2, theBook.getAuthor());
			myStmt.setString(3, theBook.getPublisher());
			myStmt.setInt(4, theBook.getYear());

			// execute sql insert
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			close(myStmt, null);
		}
	}

	public Book getBook(String theBookId) {

		Book theBook = null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int bookId;

		try {
			// convert book id to int
			bookId = Integer.parseInt(theBookId);

			// create sql to get selected book
			String sql = "SELECT * FROM db_books WHERE id=?";

			// create prepared statement
			myStmt = connection.prepareStatement(sql);

			// set params
			myStmt.setInt(1, bookId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String title = myRs.getString("title");
				String author = myRs.getString("author");
				String publisher = myRs.getString("publisher");
				int year = myRs.getInt("year");

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
			close(myStmt, myRs);
		}
		return theBook;
	}

	public void updateBook(Book theBook) {

		PreparedStatement myStmt = null;

		try {
			// create SQL update statement
			String sql = "update db_books " + "set title=?, author=?, publisher=? , year=? " + "where id=?";

			// prepare statement
			myStmt = connection.prepareStatement(sql);

			// set params
			myStmt.setString(1, theBook.getTitle());
			myStmt.setString(2, theBook.getAuthor());
			myStmt.setString(3, theBook.getPublisher());
			myStmt.setInt(4, theBook.getYear());
			myStmt.setInt(5, theBook.getId());

			// execute SQL statement
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			close(myStmt, null);
		}
	}

	public void deleteBook(String theBookId) {

		PreparedStatement myStmt = null;

		try {
			// convert book id to int
			int bookId = Integer.parseInt(theBookId);

			// create sql to delete book
			String sql = "delete from db_books where id=?";

			// prepare statement
			myStmt = connection.prepareStatement(sql);

			// set params
			myStmt.setInt(1, bookId);

			// execute sql statement
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC code
			close(myStmt, null);
		}
	}
}


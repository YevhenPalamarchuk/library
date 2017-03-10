package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibraryDaoImpl;
import model.Book;


/**
 * Servlet implementation class BookControllerServlet
 */

public class BookControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibraryDaoImpl libraryDaoImpl;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our book db util ... and pass in the conn pool / datasource
		try {
			
			libraryDaoImpl = new LibraryDaoImpl();
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			if (theCommand==null){
				listBooks(request, response);
			} else {
				// route to the appropriate method
				switch (theCommand) {
				
				case "LIST":
					listBooks(request, response);
					break;
					
				case "ADD":
					addBook(request, response);
					break;
					
				case "LOAD":
					loadBook(request, response);
					break;
					
				case "UPDATE":
					updateBook(request, response);
					break;
				
				case "DELETE":
					deleteBook(request, response);
					break;
					
				default:
					listBooks(request, response);
				}
			}
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read book id from form data
		String theBookId = request.getParameter("bookId");
		
		// delete book from database
		libraryDaoImpl.deleteBook(theBookId);
		
		// send them back to "list books" page
		listBooks(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read book info from form data
		int id = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int year = Integer.parseInt(request.getParameter("year"));
				
		// create a new book object
		Book theBook = new Book(id, title, author, publisher, year);
		
		// perform update on database
		libraryDaoImpl.updateBook(theBook);
		
		// send them back to the "list books" page
		listBooks(request, response);
		
	}

	private void loadBook(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read book id from form data
		String theBookId = request.getParameter("bookId");
		
		// get book from database (db util)
		Book theBook = libraryDaoImpl.getBook(theBookId);
		
		// place book in the request attribute
		request.setAttribute("THE_BOOK", theBook);
		
		// send to jsp page: update-book-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-book-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read book info from form data
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int year = Integer.parseInt(request.getParameter("year"));
		
		
		// create a new book object
		Book theBook = new Book(title, author, publisher, year);
		
		// add the book to the database
		libraryDaoImpl.addBook(theBook);
				
		// send back to main page (the book list)
		
		listBooks(request, response);

	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get books from db util
		List<Book> books = libraryDaoImpl.getBooks();
		
		// add books to the request
		request.setAttribute("BOOK_LIST", books);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-books.jsp");
		dispatcher.forward(request, response);
	}

}














package library;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dao.LibraryDaoImpl;
import model.Book;

public class TestLibraryDaoImpl {

	private LibraryDaoImpl libDao;
	private boolean isDBNotEmpty = true;

	@Before
	public void setUp() {
		libDao = new LibraryDaoImpl();
		if (countRowsInDB(libDao.getBooks()) > 0) {
			isDBNotEmpty = true;
		} else {
			isDBNotEmpty = false;
		}
	}

	@After
	public void dropDown() {
		libDao = null;
	}

	@Test
	public void testGetBooksIsNotNull() {

		if (isDBNotEmpty) {
			assertNotEquals("Answer from DataBase for getBooks() was null", null, libDao.getBooks());
		} else {
			Assert.fail("DB is empty.");
		}

	}

	@Test
	public void testGetBookIsNotNull() {

		if (isDBNotEmpty) {
			String lastEID = String.valueOf(lastElementID(libDao.getBooks()));
			assertNotEquals("Answer from DataBase for getBook(\"1\") was null", null, libDao.getBook(lastEID));
		} else {
			Assert.fail("DB is empty.");
		}
	}

	@Test
	public void testAddBookToDB() {
		int startCountOfRows = countRowsInDB(libDao.getBooks());
		Book testBook = new Book("TestTitle", "TestAuthor", "TestPublisher", 2016);

		libDao.addBook(testBook);

		int endCountOfRows = countRowsInDB(libDao.getBooks());
		String lastEID = String.valueOf(lastElementID(libDao.getBooks()));

		if (endCountOfRows > startCountOfRows) {
			libDao.deleteBook(lastEID);
		} else {
			Assert.fail("The book wasn't added to DB !!!");
		}
	}

	private int countRowsInDB(List<Book> bookList) {
		int countRows = bookList.size();
		return countRows;
	}

	private int lastElementID(List<Book> bookList) {

		int countRows = bookList.size();
		int lastElID = bookList.get(countRows-1).getId();
		return lastElID;
	}
}

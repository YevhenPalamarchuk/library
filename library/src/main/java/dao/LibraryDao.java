package dao;

import java.util.List;

import model.Book;

public interface LibraryDao {

	public List<Book> getBooks();

	public void addBook(Book theBook);

	public Book getBook(String theBookId);

	public void updateBook(Book theBook);

	public void deleteBook(String theBookId);
}

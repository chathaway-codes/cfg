package models;

import java.util.Date;

import org.junit.*;

import play.test.*;
import static play.test.Helpers.*;
import static org.junit.Assert.*;
import models.Book;

public class BookTests extends WithApplication {
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void testCanAddBook() {
		int initial_books = Book.find.findRowCount();
		Book b = new Book(null, "A book", "An author", new Date());
		b.save();

		// There should be exactly 1 additional book in the database
		assertEquals(Book.find.findRowCount(), initial_books + 1);
	}

	@Test
	public void testCanDeleteBook() {
		int initial_books = Book.find.findRowCount();
		Book b = new Book(null, "A book", "An author", new Date());
		b.save();

		b.delete();

		// There should be the same number of books in the database
		assertEquals(Book.find.findRowCount(), initial_books);

	}
}

package al.edu.cit.libraryms.services;

import al.edu.cit.libraryms.models.Book;
import al.edu.cit.libraryms.repositories.BookRepository;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private final Stack<Book> recentlyViewedBooks = new Stack<>();

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            addRecentlyViewedBook(book);
        }
        return book;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getRecentlyViewedBooks() {
        return Collections.unmodifiableList(recentlyViewedBooks);
    }

    private void addRecentlyViewedBook(Book book) {
        recentlyViewedBooks.remove(book); // Remove the book if it's already in the list
        recentlyViewedBooks.push(book); // Add the book to the start of the list

        if (recentlyViewedBooks.size() > 5) { // Limit the list to 5 books
            recentlyViewedBooks.remove(0);
        }
    }
}

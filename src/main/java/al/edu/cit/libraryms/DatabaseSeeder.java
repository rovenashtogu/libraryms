package al.edu.cit.libraryms;

import al.edu.cit.libraryms.models.Author;
import al.edu.cit.libraryms.models.Book;
import al.edu.cit.libraryms.services.AuthorService;
import al.edu.cit.libraryms.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        seedAuthorsAndBooks();
    }

    private void seedAuthorsAndBooks() {
        // Create authors
        Author author1 = new Author();
        author1.setName("George Orwell");
        authorService.save(author1);

        Author author2 = new Author();
        author2.setName("Jane Austen");
        authorService.save(author2);

        Author author3 = new Author();
        author3.setName("Mark Twain");
        authorService.save(author3);

        // Create books
        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setAuthor(author1);
        bookService.save(book1);

        Book book2 = new Book();
        book2.setTitle("Pride and Prejudice");
        book2.setAuthor(author2);
        bookService.save(book2);

        Book book3 = new Book();
        book3.setTitle("Adventures of Huckleberry Finn");
        book3.setAuthor(author3);
        bookService.save(book3);
    }
}


package al.edu.cit.libraryms.controllers;

import al.edu.cit.libraryms.models.Author;
import al.edu.cit.libraryms.models.Book;
import al.edu.cit.libraryms.services.AuthorService;
import al.edu.cit.libraryms.services.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model, HttpSession session) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books";
        }

        model.addAttribute("book", book);

        // Update recently viewed books in the session
        List<Book> recentlyViewedBooks = (List<Book>) session.getAttribute("recentlyViewedBooks");
        if (recentlyViewedBooks == null) {
            recentlyViewedBooks = new LinkedList<>();
        }

        recentlyViewedBooks.remove(book); // Remove the book if it's already in the list
        recentlyViewedBooks.add(0, book); // Add the book to the start of the list

        if (recentlyViewedBooks.size() > 5) { // Limit the list to 5 books
            recentlyViewedBooks.remove(recentlyViewedBooks.size() - 1);
        }

        session.setAttribute("recentlyViewedBooks", recentlyViewedBooks);

        return "viewBook";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam("title") String title, @RequestParam("author") String authorName) {
        Author author = authorService.findByName(authorName);
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            authorService.save(author);
        }
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "editBook";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/recent")
    public String recentBooks(Model model, HttpSession session) {
        List<Book> recentlyViewedBooks = (List<Book>) session.getAttribute("recentlyViewedBooks");
        if (recentlyViewedBooks == null) {
            recentlyViewedBooks = new LinkedList<>();
        }
        model.addAttribute("recentlyViewedBooks", recentlyViewedBooks);
        return "recentBooks";
    }
}

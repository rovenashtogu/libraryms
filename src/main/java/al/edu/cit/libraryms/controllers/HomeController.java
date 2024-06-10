package al.edu.cit.libraryms.controllers;

import al.edu.cit.libraryms.services.AuthorService;
import al.edu.cit.libraryms.services.BookService;
import al.edu.cit.libraryms.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BorrowerService borrowerService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Library Management System");
        model.addAttribute("bookCount", bookService.findAll().size());
        model.addAttribute("authorCount", authorService.findAll().size());
        model.addAttribute("borrowerCount", borrowerService.findAll().size());
        return "home"; // Ensure this matches the view template name
    }
}

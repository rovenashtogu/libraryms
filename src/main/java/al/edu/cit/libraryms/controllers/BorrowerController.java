package al.edu.cit.libraryms.controllers;

import al.edu.cit.libraryms.models.Borrower;
import al.edu.cit.libraryms.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @GetMapping
    public String listBorrowers(Model model) {
        model.addAttribute("borrowers", borrowerService.findAll());
        return "borrowers";
    }

    @GetMapping("/add")
    public String addBorrowerForm(Model model) {
        model.addAttribute("borrower", new Borrower());
        return "addBorrower";
    }

    @PostMapping("/add")
    public String addBorrower(@ModelAttribute Borrower borrower) {
        borrowerService.save(borrower);
        return "redirect:/borrowers";
    }

    @GetMapping("/edit/{id}")
    public String editBorrowerForm(@PathVariable Long id, Model model) {
        model.addAttribute("borrower", borrowerService.findById(id));
        return "editBorrower";
    }

    @PostMapping("/edit")
    public String editBorrower(@ModelAttribute Borrower borrower) {
        borrowerService.save(borrower);
        return "redirect:/borrowers";
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrower(@PathVariable Long id) {
        borrowerService.delete(id);
        return "redirect:/borrowers";
    }
}

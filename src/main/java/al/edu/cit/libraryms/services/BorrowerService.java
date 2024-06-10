package al.edu.cit.libraryms.services;

import al.edu.cit.libraryms.models.Borrower;
import al.edu.cit.libraryms.repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    public List<Borrower> findAll() {
        return borrowerRepository.findAll();
    }

    public Borrower findById(Long id) {
        return borrowerRepository.findById(id).orElse(null);
    }

    public void save(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    public void delete(Long id) {
        borrowerRepository.deleteById(id);
    }
}

package al.edu.cit.libraryms.repositories;

import al.edu.cit.libraryms.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {}

package al.edu.cit.libraryms.repositories;

import al.edu.cit.libraryms.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}

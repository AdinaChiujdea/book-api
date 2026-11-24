package ro.digitalnation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.digitalnation.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByPublisherId(Long publisherId);
}

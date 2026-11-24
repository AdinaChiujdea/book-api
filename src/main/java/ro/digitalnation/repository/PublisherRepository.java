package ro.digitalnation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.digitalnation.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

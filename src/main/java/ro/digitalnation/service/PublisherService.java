package ro.digitalnation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.digitalnation.dto.PublisherRequest;
import ro.digitalnation.exception.ConflictException;
import ro.digitalnation.exception.ResourceNotFoundException;
import ro.digitalnation.model.Publisher;
import ro.digitalnation.repository.BookRepository;
import ro.digitalnation.repository.PublisherRepository;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public PublisherService(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Publisher get(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editura cu id-ul " + id + " nu exista"));
    }

    @Transactional
    public Publisher create(PublisherRequest request) {
        Publisher publisher = new Publisher();
        apply(publisher, request);
        return publisherRepository.save(publisher);
    }

    @Transactional
    public Publisher update(Long id, PublisherRequest request) {
        Publisher publisher = get(id);
        apply(publisher, request);
        return publisherRepository.save(publisher);
    }

    @Transactional
    public void delete(Long id) {
        Publisher publisher = get(id);
        if (bookRepository.existsByPublisherId(id)) {
            throw new ConflictException("Editura are carti asociate si nu poate fi stearsa");
        }
        publisherRepository.delete(publisher);
    }

    private void apply(Publisher publisher, PublisherRequest request) {
        publisher.setName(request.name());
        publisher.setLocation(request.location());
        publisher.setTelephone(request.telephone());
        publisher.setEmail(request.email());
    }
}

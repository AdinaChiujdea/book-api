package ro.digitalnation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.digitalnation.dto.BookRequest;
import ro.digitalnation.exception.ResourceNotFoundException;
import ro.digitalnation.model.Book;
import ro.digitalnation.model.Publisher;
import ro.digitalnation.repository.BookRepository;
import ro.digitalnation.repository.PublisherRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book get(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cartea cu id-ul " + id + " nu exista"));
    }

    @Transactional
    public Book create(BookRequest request) {
        Book book = new Book();
        apply(book, request);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, BookRequest request) {
        Book book = get(id);
        apply(book, request);
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.delete(get(id));
    }

    private void apply(Book book, BookRequest request) {
        Publisher publisher = publisherRepository.findById(request.publisherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Editura cu id-ul " + request.publisherId() + " nu exista"));
        book.setTitle(request.title());
        book.setIsbn(request.isbn());
        book.setPublisher(publisher);
    }
}

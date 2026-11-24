package ro.digitalnation.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.digitalnation.dto.PublisherRequest;
import ro.digitalnation.model.Publisher;
import ro.digitalnation.service.PublisherService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAll() {
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public Publisher get(@PathVariable Long id) {
        return publisherService.get(id);
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@Valid @RequestBody PublisherRequest request) {
        Publisher created = publisherService.create(request);
        return ResponseEntity.created(URI.create("/publishers/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Publisher update(@PathVariable Long id, @Valid @RequestBody PublisherRequest request) {
        return publisherService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

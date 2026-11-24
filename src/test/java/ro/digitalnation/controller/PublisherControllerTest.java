package ro.digitalnation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.digitalnation.model.Publisher;
import ro.digitalnation.repository.BookRepository;
import ro.digitalnation.repository.PublisherRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    private Publisher savePublisher(String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setLocation("Brasov");
        publisher.setTelephone("0700000000");
        publisher.setEmail("test@example.com");
        return publisherRepository.save(publisher);
    }

    @Test
    void createPublisherReturns201() throws Exception {
        String json = """
                {"name": "Editura Test", "location": "Brasov", "telephone": "0700000000", "email": "a@b.ro"}
                """;

        mockMvc.perform(post("/publishers").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Editura Test")));
    }

    @Test
    void createPublisherWithInvalidEmailReturns400() throws Exception {
        String json = """
                {"name": "Editura Test", "location": "Brasov", "telephone": "0700000000", "email": "nu-e-email"}
                """;

        mockMvc.perform(post("/publishers").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.email").exists());
    }

    @Test
    void deletePublisherWithoutBooksReturns204() throws Exception {
        Publisher publisher = savePublisher("Fara carti");

        mockMvc.perform(delete("/publishers/" + publisher.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePublisherWithBooksReturns409() throws Exception {
        Long publisherId = bookRepository.findAll().get(0).getPublisher().getId();

        mockMvc.perform(delete("/publishers/" + publisherId))
                .andExpect(status().isConflict());
    }
}

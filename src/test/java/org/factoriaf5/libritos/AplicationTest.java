package org.factoriaf5.libritos;

import org.factoriaf5.libritos.repositories.Book;
import org.factoriaf5.libritos.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
    @AutoConfigureMockMvc
    class AplicationTests {

        @Autowired
        MockMvc mockMvc;

      @BeforeEach
      void setUp() {
        bookRepository.deleteAll();
      }

        @Test @WithMockUser
        void loadsTheHomePage() throws Exception {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("books/all"));
        }

    @Autowired
    BookRepository bookRepository;

    @Test @WithMockUser
    void returnsTheExistingBooks() throws Exception {

        Book book = bookRepository.save(new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021", "", "Read"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/all"))
                .andExpect(model().attribute("books", hasItem(book)));
    }

    @Test @WithMockUser
    void returnsFormToAddNewBook () throws Exception {
        mockMvc.perform(get("/books/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"));
    }


    @Test @WithMockUser
    void AllowsToCreateNewBook () throws Exception {
        mockMvc.perform(post("/books/new")
                .param("title", "Harry Potter and the Philosopher's Stone")
                .param("author", "J.K. Rowling")
                .param("category", "fantasy")
                        .with(csrf())
		    )

        .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        List<Book> existingBooks = (List<Book>) bookRepository.findAll();
        assertThat(existingBooks, contains(allOf(
                hasProperty("title", equalTo("Harry Potter and the Philosopher's Stone")),
                hasProperty("author", equalTo("J.K. Rowling")),
                hasProperty("category", equalTo("fantasy"))
        )));
    }

    @Test @WithMockUser
    void returnsAFormToAddNewBooks() throws Exception {
        mockMvc.perform(get("/books/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("title", "Create new book"));
    }

    @Test @WithMockUser
    void returnsAFormToEditBooks() throws Exception {
        Book book = bookRepository.save(new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021","", "Read"));
        mockMvc.perform(get("/books/edit/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"))
                .andExpect(model().attribute("book", book))
                .andExpect(model().attribute("title", "Edit book"));
    }

    @Test
    @WithMockUser
    void allowsToDeleteABook() throws Exception {
        Book book = bookRepository.save(new Book("Una habitación propia", "Virginia Woolf", "Essay", "https://images-na.ssl-images-amazon.com/images/I/81ufSJuG9LL.jpg",5, "March-2021", "May-2021", "","Read"));
        mockMvc.perform(get("/books/delete/" + book.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertThat(bookRepository.findById(book.getId()), equalTo(Optional.empty()));
    }


    }


package org.factoriaf5.libritos.controllers;

import org.factoriaf5.libritos.repositories.Book;
import org.factoriaf5.libritos.repositories.BookRepository;
import org.factoriaf5.libritos.repositories.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    String listBooks(Model model, @RequestParam(required = false) boolean finished) {
        List<Book> books = bookRepository.findAllByFinished((Sort.by(Sort.Direction.DESC, "finished")).and(Sort.by("id")));
        model.addAttribute("title", "My books");
        model.addAttribute("books", books);
        return "books/all";
    }

    @GetMapping ("/books/new")
    String NewBook (Model model) {
        Book book= new Book();
        model.addAttribute("book", book);
        model.addAttribute("title", "Create new book");
        return "books/edit";
    }

    @PostMapping("/books/new")
    String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    String editBook(Model model, @PathVariable Long id){
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("title", "Edit book");
        return "books/edit";
    }

    @GetMapping("/books/delete/{id}")
    String remove(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @PutMapping("/books/{id}/rating")
    public Book addRating(@RequestBody Rating rating, @PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setRating(rating.getScore());
        return bookRepository.save(book);
    }

}
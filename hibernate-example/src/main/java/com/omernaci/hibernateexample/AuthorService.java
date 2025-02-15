package com.omernaci.hibernateexample;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void printAuthorsAndBooks() {
        List<Author> authors = authorRepository.findAllWithBooks();
        for (Author author : authors) {
            System.out.println("Author: " + author.getName());
            for (Book book : author.getBooks()) {
                System.out.println("Book: " + book.getTitle());
            }
        }
    }

}

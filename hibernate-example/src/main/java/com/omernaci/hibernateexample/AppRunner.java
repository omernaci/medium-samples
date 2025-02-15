package com.omernaci.hibernateexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final AuthorService authorService;

    public AppRunner(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        authorService.printAuthorsAndBooks();
    }

}

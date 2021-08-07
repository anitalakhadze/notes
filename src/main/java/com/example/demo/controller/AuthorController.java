package com.example.demo.controller;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateAuthorRequest;
import com.example.demo.model.response.AuthorResponse;
import com.example.demo.model.response.NoteResponse;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponse> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @GetMapping("/{id}/notes")
    public List<NoteResponse> getNotesByAuthorId(@PathVariable Long id) {
        return authorService.getNotesByAuthorId(id);
    }

    @PostMapping
    public Long saveAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
        return authorService.addAuthor(createAuthorRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    public void replaceAuthor(@PathVariable Long id, @RequestBody Author author) {
        authorService.updateAuthor(id, author);
    }
}

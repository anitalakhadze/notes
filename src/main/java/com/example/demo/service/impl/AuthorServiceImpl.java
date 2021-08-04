package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateAuthorRequest;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorRepository authorRepository;
    private final NoteRepository notesRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, NoteRepository notesRepository) {
        this.authorRepository = authorRepository;
        this.notesRepository = notesRepository;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public List<Note> getNotesByAuthorId(Long id) {
        return notesRepository.findAllByAuthor_Id(id);
    }

    @Override
    public Long addAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = new Author();
        author.setUsername(createAuthorRequest.getUsername());
        return authorRepository.save(author).getId();
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        authorRepository.findById(id)
                .map(oldAuthor -> {
                    oldAuthor.setNotes(author.getNotes());
                    oldAuthor.setUsername(author.getUsername());
                    return authorRepository.save(oldAuthor);
                })
                .orElseGet(() -> {
                    author.setId(id);
                    return authorRepository.save(author);
                });
    }
}

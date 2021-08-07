package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Note;
import com.example.demo.model.error.ApiException;
import com.example.demo.model.error.ErrorCode;
import com.example.demo.model.request.CreateAuthorRequest;
import com.example.demo.model.response.AuthorResponse;
import com.example.demo.model.response.NoteResponse;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<AuthorResponse> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(AuthorResponse::fromAuthor).collect(Collectors.toList());
    }

    @Override
    public AuthorResponse getAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND));
        return AuthorResponse.builder()
                .username(author.getUsername()).build();
    }

    @Override
    public List<NoteResponse> getNotesByAuthorId(Long id) {
        return notesRepository
                .findAllByAuthor_Id(id)
                .stream()
                .map(NoteResponse::fromNote)
                .collect(Collectors.toList());
    }

    @Override
    public Long addAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = Author.builder()
                .username(createAuthorRequest.getUsername()).build();
        return authorRepository.save(author).getId();
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND));
        authorRepository.delete(author);
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

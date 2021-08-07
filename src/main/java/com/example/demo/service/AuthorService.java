package com.example.demo.service;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateAuthorRequest;
import com.example.demo.model.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    List<AuthorResponse> getAuthors();

    AuthorResponse getAuthor(Long id);

    List<Note> getNotesByAuthorId(Long id);

    Long addAuthor(CreateAuthorRequest createAuthorRequest);

    void deleteAuthor(Long id);

    void updateAuthor(Long id, Author author);
}

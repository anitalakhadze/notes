package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateNoteRequest;
import com.example.demo.model.request.UpdateNoteRequest;
import com.example.demo.model.response.NoteResponse;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NotesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteServiceImpl implements NotesService {

    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, AuthorRepository authorRepository) {
        this.noteRepository = noteRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<NoteResponse> getNotes() {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(NoteResponse::fromNote).collect(Collectors.toList());
    }

    @Override
    public NoteResponse getNote(Long id) {
        log.info("Getting note detailed info for id {}", id);
        Note note = noteRepository.getById(id);
        return NoteResponse.fromNote(note);
    }

    @Override
    public Long addNote(CreateNoteRequest createNoteRequest) {
        Author author = authorRepository.getById(createNoteRequest.getAuthorId());
        if (author == null) {

        }

        Note note = Note.builder()
                .subject(createNoteRequest.getSubject())
                .text(createNoteRequest.getSubject())
                .author(author)
                .createdAt(createNoteRequest.getCreatedAt()).build();

        return noteRepository.save(note).getId();
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void updateNote(Long id, UpdateNoteRequest updateNoteRequest) {
        noteRepository.findById(id)
                .map(oldNote -> {
                    oldNote.setCreatedAt(updateNoteRequest.getCreatedAt());
                    oldNote.setText(updateNoteRequest.getText());
                    oldNote.setSubject(updateNoteRequest.getSubject());
                    return noteRepository.save(oldNote);
                })
                .orElseGet(() -> {
                    Author author = authorRepository.getById(updateNoteRequest.getAuthorId());
                    Note newNote = Note.builder()
                            .author(author)
                            .subject(updateNoteRequest.getSubject())
                            .text(updateNoteRequest.getText())
                            .createdAt(updateNoteRequest.getCreatedAt()).build();
                    return noteRepository.save(newNote);
                });
    }
}

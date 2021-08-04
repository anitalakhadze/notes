package com.example.demo.service.impl;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateNoteRequest;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NotesService {

    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, AuthorRepository authorRepository) {
        this.noteRepository = noteRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNote(Long id) {
        return noteRepository.getById(id);
    }

    @Override
    public Long addNote(CreateNoteRequest createNoteRequest) {
        Author author = authorRepository.getById(createNoteRequest.getAuthorId());
        if (author == null) {

        }

        Note note = new Note();
        note.setSubject(createNoteRequest.getSubject());
        note.setText(createNoteRequest.getText());
        note.setCreatedAt(createNoteRequest.getCreatedAt());
        note.setAuthor(author);

        return noteRepository.save(note).getId();
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void updateNote(Long id, Note note) {
        noteRepository.findById(id)
                .map(oldNote -> {
                    oldNote.setAuthor(note.getAuthor());
                    oldNote.setCreatedAt(note.getCreatedAt());
                    oldNote.setText(note.getText());
                    oldNote.setSubject(note.getSubject());
                    return noteRepository.save(oldNote);
                })
                .orElseGet(() -> {
                    note.setId(id);
                    return noteRepository.save(note);
                });
    }
}

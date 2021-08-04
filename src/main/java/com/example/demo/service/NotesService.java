package com.example.demo.service;

import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateNoteRequest;

import java.util.List;

public interface NotesService {

    List<Note> getNotes();

    Note getNote(Long id);

    Long addNote(CreateNoteRequest note);

    void deleteNote(Long id);

    void updateNote(Long id, Note note);

}

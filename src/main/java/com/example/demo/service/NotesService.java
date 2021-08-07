package com.example.demo.service;

import com.example.demo.model.request.CreateNoteRequest;
import com.example.demo.model.request.UpdateNoteRequest;
import com.example.demo.model.response.NoteResponse;

import java.util.List;

public interface NotesService {

    List<NoteResponse> getNotes();

    NoteResponse getNote(Long id);

    Long addNote(CreateNoteRequest note);

    void deleteNote(Long id);

    void updateNote(Long id, UpdateNoteRequest updateNoteRequest);

}

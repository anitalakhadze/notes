package com.example.demo.controller;

import com.example.demo.model.entity.Note;
import com.example.demo.model.request.CreateNoteRequest;
import com.example.demo.model.request.UpdateNoteRequest;
import com.example.demo.model.response.NoteResponse;
import com.example.demo.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NotesService notesService;

    @Autowired
    public NoteController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public List<NoteResponse> getNotes() {
        return notesService.getNotes();
    }

    @GetMapping("/{id}")
    public NoteResponse getNote(@PathVariable Long id) {
        return notesService.getNote(id);
    }

    @PostMapping
    public Long saveNote(@RequestBody CreateNoteRequest createNoteRequest) {
        return notesService.addNote(createNoteRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
    }

    @PutMapping("/{id}")
    public void replaceNote(@PathVariable Long id, @RequestBody UpdateNoteRequest updateNoteRequest) {
        notesService.updateNote(id, updateNoteRequest);
    }
}

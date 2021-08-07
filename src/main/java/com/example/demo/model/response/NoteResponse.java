package com.example.demo.model.response;

import com.example.demo.model.entity.Note;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NoteResponse {

    private Long id;

    private String subject;

    private String text;

    private String authorUsername;

    private Date createdAt;

    public static NoteResponse fromNote(Note note) {
        return new NoteResponseBuilder()
                .id(note.getId())
                .authorUsername(note.getAuthor().getUsername())
                .subject(note.getSubject())
                .text(note.getText())
                .createdAt(note.getCreatedAt()).build();
    }
}

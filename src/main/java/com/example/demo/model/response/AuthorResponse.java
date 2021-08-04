package com.example.demo.model.response;

import com.example.demo.model.entity.Note;
import lombok.Data;

import java.util.List;

@Data
public class AuthorResponse {

    private Long id;

    private String username;

    private List<Note> notes;

}

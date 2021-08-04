package com.example.demo.model.response;

import com.example.demo.model.entity.Author;
import lombok.Data;

import java.util.Date;

@Data
public class NoteResponse {

    private Long id;

    private String subject;

    private String text;

    private Author author;

    private Date createdAt;

}

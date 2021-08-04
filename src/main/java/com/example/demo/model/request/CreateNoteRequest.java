package com.example.demo.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateNoteRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    private Date createdAt;

    @NotNull
    private Long authorId;
}

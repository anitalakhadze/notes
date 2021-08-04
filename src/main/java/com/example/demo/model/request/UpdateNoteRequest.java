package com.example.demo.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class UpdateNoteRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    private Date createdAt;
}

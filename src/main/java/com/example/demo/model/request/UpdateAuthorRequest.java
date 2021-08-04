package com.example.demo.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateAuthorRequest {

    @NotBlank
    private String username;

}

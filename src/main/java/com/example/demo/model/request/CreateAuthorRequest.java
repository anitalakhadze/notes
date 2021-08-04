package com.example.demo.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateAuthorRequest {

    @NotBlank
    private String username;

}

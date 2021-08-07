package com.example.demo.model.response;

import com.example.demo.model.entity.Author;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponse {

    private Long id;

    private String username;

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponseBuilder()
                .id(author.getId())
                .username(author.getUsername()).build();
    }

}

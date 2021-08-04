package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Author {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @OneToMany
    private List<Note> notes;

}

package com.example.demo.model.entity;

import com.example.demo.model.entity.Author;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Note {

    @Id @GeneratedValue
    private Long id;

    private String subject;

    private String text;

    @ManyToOne
    private Author author;

    private Date createdAt;

}

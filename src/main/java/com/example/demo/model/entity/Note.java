package com.example.demo.model.entity;

import com.example.demo.model.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_generator")
    @SequenceGenerator(name = "note_id_generator", sequenceName = "note_id_seq", allocationSize = 1)
    private Long id;

    private String subject;

    private String text;

    @ManyToOne
    private Author author;

    private Date createdAt;

}

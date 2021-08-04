package com.example.demo.repository;

import com.example.demo.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByAuthor_Id(Long id);

}

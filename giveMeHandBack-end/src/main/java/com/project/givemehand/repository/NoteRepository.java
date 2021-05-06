package com.project.givemehand.repository;

import com.project.givemehand.models.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}

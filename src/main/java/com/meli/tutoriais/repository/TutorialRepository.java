package com.meli.tutoriais.repository;

import com.meli.tutoriais.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findAllByStatus(String status);
    List<Tutorial> findAllByTituloContaining(String title);
}

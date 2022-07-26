package com.meli.tutoriais.service;

import com.meli.tutoriais.dto.TutorialDto;
import com.meli.tutoriais.model.Tutorial;

import java.util.List;
import java.util.Optional;

public interface TutorialServiceInterface {
    Tutorial save(TutorialDto tutorialDto);
    List<Tutorial> findAll();
    Optional<Tutorial> findById(Long id);
    Tutorial update(Long id, TutorialDto tutorialDto);
    void delete(Long id);
    void deleteAll();
    List<Tutorial> findAllPublished();
    List<Tutorial> findAllByTituloContaining(String title);
}

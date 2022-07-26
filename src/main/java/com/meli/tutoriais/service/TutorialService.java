package com.meli.tutoriais.service;

import com.meli.tutoriais.dto.TutorialDto;
import com.meli.tutoriais.model.Tutorial;
import com.meli.tutoriais.repository.TutorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService implements TutorialServiceInterface{

    private final TutorialRepository repository;

    public TutorialService(TutorialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tutorial save(TutorialDto tutorialDto) {
        return repository.save(Tutorial.builder()
                .titulo(tutorialDto.getTitulo())
                .descricao(tutorialDto.getDescricao())
                .status(tutorialDto.getStatus())
                .build());
    }

    @Override
    public List<Tutorial> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Tutorial> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Tutorial update(Long id, TutorialDto tutorialDto) {
        Tutorial tutorial = repository.getReferenceById(id);
        tutorial.setTitulo(tutorialDto.getTitulo());
        tutorial.setDescricao(tutorialDto.getDescricao());
        tutorial.setStatus(tutorialDto.getStatus());
        return repository.save(tutorial);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Tutorial> findAllPublished() {
        return repository.findAllByStatus("published");
    }

    @Override
    public List<Tutorial> findAllByTituloContaining(String title) {
        return repository.findAllByTituloContaining(title);
    }
}

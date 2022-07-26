package com.meli.tutoriais.controller;

import com.meli.tutoriais.dto.TutorialDto;
import com.meli.tutoriais.model.Tutorial;
import com.meli.tutoriais.service.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService service;

    public TutorialController(TutorialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tutorial> save(@RequestBody TutorialDto tutorialDto) {
        return new ResponseEntity<>(service.save(tutorialDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tutorial>> findAll(@RequestParam(value = "title", required = false) String title) {
        return title == null ? ResponseEntity.ok(service.findAll()) : ResponseEntity.ok(service.findAllByTituloContaining(title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(testCase -> ResponseEntity.ok().body(testCase))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> findAllPublished() {
        return ResponseEntity.ok(service.findAllPublished());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> update(@PathVariable Long id, @RequestBody TutorialDto tutorialDto) {
        return service.findById(id)
                .map(testCase -> ResponseEntity.ok(service.update(id, tutorialDto)))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(testCase -> {
                    service.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}

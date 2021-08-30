package com.java.tutorialservice.controller;

import com.java.tutorialservice.model.Tutorial;
import com.java.tutorialservice.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TutorialController {

  @Autowired
  private TutorialRepository tutorialRepository;

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
    return ResponseEntity.ok(tutorialRepository.findAll());
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    return ResponseEntity.ok(tutorialRepository.getById(id));
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    return ResponseEntity.ok(tutorialRepository.save(tutorial));
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    Tutorial t = tutorial;
    t.setId(id);
    return ResponseEntity.ok(tutorialRepository.save(t));
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    tutorialRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    tutorialRepository.deleteAll();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    return ResponseEntity.ok(tutorialRepository.findByPublished(true));
  }
}
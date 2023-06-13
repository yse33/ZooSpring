package com.example.zoo.controller;

import com.example.zoo.controller.resources.AnimalResource;
import com.example.zoo.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/{id}/audits")
    public ResponseEntity<?> findAllAudits(@PathVariable long id) {
        return ResponseEntity.ok(service.findAllAudits(id));
    }

    @GetMapping("/species/{species}")
    public ResponseEntity<?> findAllAnimalsBySpecies(@PathVariable String species) {
        return ResponseEntity.ok(service.findAllAnimalsBySpecies(species));
    }

    @GetMapping("/food/{food}")
    public ResponseEntity<?> findAllAnimalsByFood(@PathVariable String food) {
        return ResponseEntity.ok(service.findAllAnimalsByFood(food));
    }

    @GetMapping("/cage/{cage}")
    public ResponseEntity<?> findAllAnimalsByCage(@PathVariable String cage) {
        return ResponseEntity.ok(service.findAllAnimalsByCage(cage));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnimalResource resource) {
        AnimalResource saved = service.save(resource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/animals/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody AnimalResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}

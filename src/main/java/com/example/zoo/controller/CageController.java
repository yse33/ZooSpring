package com.example.zoo.controller;

import com.example.zoo.controller.resources.CageResource;
import com.example.zoo.service.CageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/cages")
@RequiredArgsConstructor
public class CageController {
    private final CageService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CageResource resource) {
        CageResource saved = service.save(resource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/animals/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CageResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}

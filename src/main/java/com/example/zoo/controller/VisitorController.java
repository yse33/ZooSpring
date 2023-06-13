package com.example.zoo.controller;

import com.example.zoo.controller.resources.VisitorResource;
import com.example.zoo.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/visitors")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService service;

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

    @GetMapping("/year/{year}")
    public ResponseEntity<?> findAllVisitorsByYear(@PathVariable int year) {
        return ResponseEntity.ok(service.findAllVisitorsByYear(year));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VisitorResource resource) {
        VisitorResource saved = service.save(resource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/animals/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody VisitorResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}

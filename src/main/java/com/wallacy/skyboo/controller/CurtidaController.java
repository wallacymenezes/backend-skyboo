package com.wallacy.skyboo.controller;

import com.wallacy.skyboo.model.Curtida;
import com.wallacy.skyboo.repository.CurtidaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/curtidas")
public class CurtidaController {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Curtida>> getAll() {
        return ResponseEntity.ok(curtidaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Curtida>> getByItemId(@PathVariable Long id) {
        return ResponseEntity.ok(curtidaRepository.findByItemId(id));
    }

    @PostMapping
    public ResponseEntity<Curtida> post(@Valid @RequestBody Curtida curtida) {
        return ResponseEntity.status(201).body(curtidaRepository.save(curtida));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Curtida> curtida = curtidaRepository.findById(id);

        if (curtida.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        curtidaRepository.deleteById(id);
    }
}

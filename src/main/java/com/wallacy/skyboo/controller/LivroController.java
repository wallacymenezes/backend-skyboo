package com.wallacy.skyboo.controller;

import com.wallacy.skyboo.model.Livro;
import com.wallacy.skyboo.repository.LivroRepository;
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
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> getAll() {
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getById(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Livro>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(livroRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Livro> post(@Valid @RequestBody Livro livro) {
        return ResponseEntity.status(201).body(livroRepository.save(livro));
    }

    @PutMapping
    public ResponseEntity<Livro> put(@Valid @RequestBody Livro livro) {
        return livroRepository.findById(livro.getId())
                .map(response -> ResponseEntity.status(201).body(livroRepository.save(livro)))
                .orElse(ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        livroRepository.deleteById(id);
    }
}

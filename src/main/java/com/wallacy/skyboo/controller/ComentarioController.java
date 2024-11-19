package com.wallacy.skyboo.controller;

import com.wallacy.skyboo.model.Comentario;
import com.wallacy.skyboo.repository.ComentarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getById(@PathVariable Long id) {
        return comentarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PostMapping
    public ResponseEntity<Comentario> post(@Valid @RequestBody Comentario comentario) {
        return ResponseEntity.status(201).body(comentarioRepository.save(comentario));
    }

    @PutMapping
    public ResponseEntity<Comentario> put(@Valid @RequestBody Comentario comentario) {
        return comentarioRepository.findById(comentario.getId())
                .map(response -> ResponseEntity.status(201).body(comentarioRepository.save(comentario)))
                .orElse(ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);

        if (comentario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        comentarioRepository.deleteById(id);
    }
}

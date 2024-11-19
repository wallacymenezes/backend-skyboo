package com.wallacy.skyboo.controller;

import com.wallacy.skyboo.model.Avaliacao;
import com.wallacy.skyboo.repository.AvaliacaoRepository;
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
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Avaliacao>> getAll() {
        return ResponseEntity.ok(avaliacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getById(@PathVariable Long id) {
        return avaliacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> post(@Valid  @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(201).body(avaliacaoRepository.save(avaliacao));
    }

    @PutMapping
    public ResponseEntity<Avaliacao> put(@Valid @RequestBody Avaliacao avaliacao) {
        return avaliacaoRepository.findById(avaliacao.getId())
                .map(response -> ResponseEntity.status(201).body(avaliacaoRepository.save(avaliacao)))
                .orElse(ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (avaliacao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        avaliacaoRepository.deleteById(id);
    }

}

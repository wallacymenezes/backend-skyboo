package com.wallacy.skyboo.controller;

import com.wallacy.skyboo.model.Usuario;
import com.wallacy.skyboo.model.UsuarioLogin;
import com.wallacy.skyboo.repository.UsuarioRepository;
import com.wallacy.skyboo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping("/{seguidorId}/seguir/{seguidoId}")
    public ResponseEntity<String> seguirUsuario(@PathVariable Long seguidorId, @PathVariable Long seguidoId) {
        return ResponseEntity.ok(usuarioService.seguirUsuario(seguidorId, seguidoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> getByName(@PathVariable String nome) {
        return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<Usuario>> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioRepository.findAllByUsernameContainingIgnoreCase(username));
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> authenticateUsuarios(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
        return usuarioService.authenticateUsuarios(usuarioLogin)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

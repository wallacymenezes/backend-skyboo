package com.wallacy.skyboo.controller;

import com.wallacy.skyboo.model.UsuarioLogin;
import com.wallacy.skyboo.model.reset.CodeConfirm;
import com.wallacy.skyboo.model.reset.EmailRequest;
import com.wallacy.skyboo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para solicitar o envio do otp de recuperação
    @PostMapping("/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailRequest emailRequest) {
        authService.createRecoverToken(emailRequest.getEmail());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<Optional<UsuarioLogin>> validateOtp(@Valid @RequestBody CodeConfirm codeConfirm) {
        Optional<UsuarioLogin> usuarioLogin = authService.validateOtpAndGenerateToken(codeConfirm.getOtp(), codeConfirm.getEmail());
        return ResponseEntity.ok(usuarioLogin);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody UsuarioLogin usuarioLogin) {
        authService.changePassword(usuarioLogin);
        return ResponseEntity.noContent().build();
    }
}
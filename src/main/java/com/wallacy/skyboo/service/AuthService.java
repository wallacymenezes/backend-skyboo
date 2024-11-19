package com.wallacy.skyboo.service;

import com.wallacy.skyboo.model.Usuario;
import com.wallacy.skyboo.model.UsuarioLogin;
import com.wallacy.skyboo.model.reset.PasswordRecover;
import com.wallacy.skyboo.repository.PasswordRecoverRepository;
import com.wallacy.skyboo.repository.UsuarioRepository;
import com.wallacy.skyboo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

    private Long otpExpirationMinutes = 15L;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService;

    // Gera e envia o otp de recuperação por e-mail
    public void createRecoverToken(String email) {

        if (usuarioRepository.findByEmail(email).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado", null);
        }

        String otp = generateOtp();
        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(email);
        entity.setOtp(otp);
        entity.setExpiration(Instant.now().plusSeconds(otpExpirationMinutes * 60L));
        passwordRecoverRepository.save(entity);

        String text = "Seu código de redefinição de senha: \n\n"
                + otp + ". Validade de " + otpExpirationMinutes + " minutos";

        emailService.sendEmail(email, "Recuperação de Senha", text);
    }

    public Optional<UsuarioLogin> validateOtpAndGenerateToken(String otp, String email) {
        PasswordRecover recoveryRequest = passwordRecoverRepository.findValidTokens(otp, Instant.now())
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or expired OTP"));

        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));

        Optional<UsuarioLogin> usuarioLogin = Optional.of(new UsuarioLogin());

        if(usuario.isPresent()) {

            usuarioLogin.get().setId(usuario.get().getId());
            usuarioLogin.get().setNome(usuario.get().getNome());
            usuarioLogin.get().setFoto(usuario.get().getFoto());
            usuarioLogin.get().setUsername(usuario.get().getUsername());
            usuarioLogin.get().setBiografia(usuario.get().getBiografia());
            usuarioLogin.get().setAtivo(true);
            usuarioLogin.get().setToken(generateToken(usuarioLogin.get().getEmail()));
            usuarioLogin.get().setSenha("");

            return usuarioLogin;
        }
        return Optional.empty();
    }

    public void changePassword(UsuarioLogin usuarioLogin) {
        Usuario usuario = usuarioRepository.findByEmail(usuarioLogin.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Adicione um log para verificar o valor retornado
        System.out.println("Usuario encontrado: " + usuario);

        if (usuarioLogin.getFoto().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na validação da OTP");
        }

        usuario.setSenha(passwordEncoder.encode(usuarioLogin.getSenha()));
        usuarioRepository.save(usuario);
    }

    private String generateOtp() {
        return String.format("%06d", (int)(Math.random() * 1000000)); // OTP de 6 dígitos
    }

    private String generateToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }
}

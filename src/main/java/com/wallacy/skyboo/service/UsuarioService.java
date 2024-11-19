package com.wallacy.skyboo.service;

import com.wallacy.skyboo.model.Usuario;
import com.wallacy.skyboo.model.UsuarioLogin;
import com.wallacy.skyboo.repository.UsuarioRepository;
import com.wallacy.skyboo.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public String seguirUsuario(Long seguidorId, Long seguidoId) {
        if (seguidorId.equals(seguidoId)) {
            return "Um usuário não pode seguir a si mesmo.";
        }

        Optional<Usuario> optionalSeguidor = usuarioRepository.findById(seguidorId);
        Optional<Usuario> optionalSeguido = usuarioRepository.findById(seguidoId);

        if (optionalSeguidor.isEmpty() || optionalSeguido.isEmpty()) {
            return "Usuário não encontrado.";
        }

        Usuario seguidor = optionalSeguidor.get();
        Usuario seguido = optionalSeguido.get();

        if (seguidor.getSeguindo().contains(seguido)) {
            return "Você já segue esse usuário.";
        }

        // Adiciona o usuário seguido à lista de "seguindo" do seguidor
        seguidor.getSeguindo().add(seguido);

        // Adiciona o usuário seguidor à lista de "seguidores" do seguido
        seguido.getSeguidores().add(seguidor);

        // Salva as alterações no banco de dados
        usuarioRepository.save(seguidor);
        usuarioRepository.save(seguido);

        return "Usuário seguido com sucesso.";
    }

    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            return Optional.empty();

        usuario.setSenha(encryptPassword(usuario.getSenha()));

        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario) {

        if (usuarioRepository.findById(usuario.getId()).isPresent()) {

            Optional<Usuario> searchUsuario = usuarioRepository.findByEmail(usuario.getEmail());

            if((searchUsuario.isPresent()) && (!Objects.equals(searchUsuario.get().getId(), usuario.getId())))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não existe!", null);

            usuario.setSenha(encryptPassword(usuario.getSenha()));

            return Optional.ofNullable(usuarioRepository.save(usuario));
        }

        return Optional.empty();
    }

    public Optional<UsuarioLogin> authenticateUsuarios(Optional<UsuarioLogin> usuarioLogin){

        var credentials = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha());

        Authentication authentication = authenticationManager.authenticate(credentials);

        if (authentication.isAuthenticated()) {

            Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

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
        }

        return Optional.empty();
    }

    private String encryptPassword(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }

    private String generateToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }
}

package com.wallacy.skyboo.repository;

import com.wallacy.skyboo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public List<Usuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    public Optional<Usuario> findByUsuario(String usuario);

    public Optional<Usuario> findByUsername(String username);
}

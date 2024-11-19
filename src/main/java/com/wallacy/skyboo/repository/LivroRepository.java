package com.wallacy.skyboo.repository;

import com.wallacy.skyboo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    public List<Livro> findAllByTituloContainingIgnoreCase(String titulo);
}

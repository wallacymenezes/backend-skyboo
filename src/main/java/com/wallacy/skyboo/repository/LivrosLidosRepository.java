package com.wallacy.skyboo.repository;

import com.wallacy.skyboo.model.LivrosLidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivrosLidosRepository extends JpaRepository<LivrosLidos, Long> {
}

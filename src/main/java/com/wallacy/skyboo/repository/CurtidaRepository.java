package com.wallacy.skyboo.repository;

import com.wallacy.skyboo.model.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
}

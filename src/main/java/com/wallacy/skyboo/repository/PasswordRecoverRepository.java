package com.wallacy.skyboo.repository;


import com.wallacy.skyboo.model.reset.PasswordRecover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {

}

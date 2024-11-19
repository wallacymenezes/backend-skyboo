package com.wallacy.skyboo.repository;


import com.wallacy.skyboo.model.reset.PasswordRecover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {

    @Query("SELECT r FROM PasswordRecover r WHERE r.otp = :token AND r.expiration > :now")
    List<PasswordRecover> findValidTokens(@Param("otp") String otp, @Param("now") Instant now);
}

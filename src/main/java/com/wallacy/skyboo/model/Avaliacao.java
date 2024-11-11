package com.wallacy.skyboo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "A nota deve ser no mínimo 0")
    @Max(value = 5, message = "A nota deve ser no máximo 5")
    private Double nota;

    @NotBlank(message = "O texto da avaliação não pode estar vazio")
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;
}

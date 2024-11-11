package com.wallacy.skyboo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JsonIgnoreProperties("livrosPublicados")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("avaliacoes")
    private Livro livro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "avaliacao", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("avaliacao")
    private List<Comentario> comentarios;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "avaliacao", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("avaliacao")
    private List<Curtida> usuariosCurtiram;
}

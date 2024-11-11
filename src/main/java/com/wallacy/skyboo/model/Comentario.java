package com.wallacy.skyboo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo texto é obrigatório")
    @Size(min = 1, max = 255, message = "O texto deve ter entre 1 e 255 caracteres")
    private String texto;

    @NotBlank(message = "O atributo comentarioPai é obrigatório")
    private Long comentarioPai; // ID do comentário pai para encadeamento

    @UpdateTimestamp
    private LocalDateTime data;

    @ManyToOne
    @JsonIgnoreProperties("comentarios")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("comentarios")
    private Avaliacao avaliacao;
}

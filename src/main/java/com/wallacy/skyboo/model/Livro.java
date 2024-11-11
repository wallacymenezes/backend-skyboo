package com.wallacy.skyboo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo titulo é obrigatório")
    @Size(min = 3, max = 255, message = "O titulo deve ter entre 3 caracteres a 255 caracteres")
    private String titulo;

    @NotBlank(message = "O atributo descricao é obrigatório")
    @Size(min = 10, max = 1000, message = "O atributo descrição deve ter entre 10 caracteres a 1000 caracteres")
    private String descricao;

    @NotBlank(message = "O atributo autor é obrigatório")
    @Size(max = 255, message = "O autor deve ter entre 3 caracteres a 255 caracteres")
    private String autor;

    @NotBlank(message = "O atributo editora é obrigatório")
    @Size(max = 255, message = "A editora deve ter no máximo 255 caracteres")
    private String editora;

    @NotBlank(message = "O atributo ano é obrigatório")
    @Size(min = 4, max = 4, message = "O ano deve ter 4 caracteres")
    private String ano;

    @Size(max = 1000, message = "O atributo urlCapa tem um limite de 1000 caracteres")
    private String urlCapa;

    @Size(max = 5000, message = "O atributo urlPdf tem um limite de 1000 caracteres")
    private String urlPdf;

    @Min(value = 0, message = "A nota deve ser no mínimo 0")
    @Max(value = 5, message = "A nota deve ser no máximo 5")
    private Double avaliacaoMedia;

    @UpdateTimestamp
    private LocalDateTime dataPublicacao;
}

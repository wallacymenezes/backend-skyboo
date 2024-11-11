package com.wallacy.skyboo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(min = 8, message = "The atribute password must have at least 8 characters")
    private String nome;

    @NotBlank(message = "O atributo descricao é obrigatório")
    @Size(min = 10, max = 1000, message = "O atributo descrição deve ter entre 10 caracteres a 1000 caracteres")
    private String descricao;

}

package com.wallacy.skyboo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 caracteres a 255 caracteres")
    private String nome;

    @Email(message = "Deve ser um email válido")
    @NotEmpty(message = "Email não pode ser vazio")
    private String email;

    @NotBlank(message = "O atributo username é obrigatório")
    @Size(min = 3, max = 255, message = "O username deve ter entre 3 caracteres a 255 caracteres")
    private String username;

    @NotBlank(message = "O atributo senha é obrigatório")
    @Size(min = 8, message = "O atributo senha deve ter no mínimo 8 caracteres")
    private String senha;

    @Size(max = 1000, message = "A foto deve ter no máximo 1000 caracteres")
    private String foto;
}

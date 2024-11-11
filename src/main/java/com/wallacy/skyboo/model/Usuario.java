package com.wallacy.skyboo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wallacy.skyboo.model.enums.TipoItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Size(max = 255, message = "A biografia deve ter no máximo 255 caracteres")
    private String biografia;

    private boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Livro> livrosPublicados;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Comentario> comentarios;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Avaliacao> avaliacoes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Curtida> curtidas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<LivrosLidos> livrosLidos;

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Livro> getLivrosPublicados() {
        return livrosPublicados;
    }

    public void setLivrosPublicados(List<Livro> livrosPublicados) {
        this.livrosPublicados = livrosPublicados;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Curtida> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    public List<LivrosLidos> getLivrosLidos() {
        return livrosLidos;
    }

    public void setLivrosLidos(List<LivrosLidos> livrosLidos) {
        this.livrosLidos = livrosLidos;
    }

    public List<Curtida> getCurtidasLivro() {
        try {
            if (curtidas == null) {
                return new ArrayList<>();
            }

            return curtidas.stream()
                    .filter(curtida -> curtida.getTipoItem() == TipoItem.LIVRO)
                    .collect(Collectors.toList());

        } catch (Exception e) {

            System.err.println("Erro ao filtrar as curtidas do tipo LIVRO: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}

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
    private List<CurtidasAvaliacao> curtidas;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}

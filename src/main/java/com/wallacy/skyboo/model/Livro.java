package com.wallacy.skyboo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JsonIgnoreProperties("livrosPublicados")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_livro_categoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @JsonIgnoreProperties("livros")
    private List<Categoria> categorias;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "livro", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("livro")
    private List<Avaliacao> avaliacoes;

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getUrlCapa() {
        return urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public Double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(Double avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}

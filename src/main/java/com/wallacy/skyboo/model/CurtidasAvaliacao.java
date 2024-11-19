package com.wallacy.skyboo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_curtida_avaliacao")
public class CurtidasAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliacao_id", nullable = false)
    private Avaliacao avaliacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_curtida_avaliacao_curtida",
            joinColumns = @JoinColumn(name = "curtida_avaliacao_id"),
            inverseJoinColumns = @JoinColumn(name = "curtida_id")
    )
    private List<Curtida> curtidas;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Curtida> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Curtida> curtidas) {
        this.curtidas = curtidas;
    }
}
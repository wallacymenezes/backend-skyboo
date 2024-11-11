package com.wallacy.skyboo.model;

import com.wallacy.skyboo.model.enums.TipoItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_curtida")
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo itemId é obrigatório")
    private Long itemId;

    @NotBlank(message = "O atributo tipoItem é obrigatório")
    @Enumerated(EnumType.STRING) // Usando o tipo ENUM
    private TipoItem tipoItem;

    @UpdateTimestamp
    private LocalDateTime data;


}

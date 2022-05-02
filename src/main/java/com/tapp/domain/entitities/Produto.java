package com.tapp.domain.entitities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Campo descricao é Obrigatorio")
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "Campo Preco é Obrigatorio")
    @Column(name = "preco_unitario")
    private BigDecimal preco;

}

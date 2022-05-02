package com.tapp.domain.entitities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Campo nome é Obrigatório")
    @Column(name = "nome", length = 100)
    private String nome;

    @NotEmpty(message = "Campo CPF é Obrigatório")
    @CPF(message = "Informe um CPF valido")
    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente" ,fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

}

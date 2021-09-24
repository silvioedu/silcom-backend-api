package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tblprodutocomplemento")
public class ProdutoComplemento {
 
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    
    @CreationTimestamp
    private OffsetDateTime dataAtualizacao;

    public void format() {
        this.sigla = this.sigla.toUpperCase();
    }
}

package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Data
@Entity
@Table(name = "tblcliente")
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    private String razaoSocial;

    private String nomeFantasia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ramo")
    private Ramo ramo;

    private String tipoPessoa;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    Cliente() {
        // default constructor
    }

    public Cliente(Long id, String razaoSocial, String nomeFantasia, Ramo ramo, String tipoPessoa, String observacoes, OffsetDateTime dataCriacao, OffsetDateTime dataAtualizacao) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.ramo = ramo;
        this.tipoPessoa = tipoPessoa;
        this.observacoes = observacoes;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

}

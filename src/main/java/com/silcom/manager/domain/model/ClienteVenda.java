package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "tblclientevenda")
public class ClienteVenda {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")    
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private VendaStatus status;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento_tipo")    
    private FormaPagamentoTipo formaPagamentoTipo;

    private BigDecimal desconto;

    private BigDecimal agravo;

    private BigDecimal valorTotal;

    private boolean emitirNota;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    ClienteVenda() {
        // default constructor
    }

    public ClienteVenda(Long id, Cliente cliente, VendaStatus status, FormaPagamentoTipo formaPagamentoTipo, BigDecimal desconto, BigDecimal agravo, BigDecimal valorTotal, boolean emitirNota, String observacoes, OffsetDateTime dataCriacao, OffsetDateTime dataAtualizacao) {
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.formaPagamentoTipo = formaPagamentoTipo;
        this.desconto = desconto;
        this.agravo = agravo;
        this.valorTotal = valorTotal;
        this.emitirNota = emitirNota;
        this.observacoes = observacoes;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }
    

}

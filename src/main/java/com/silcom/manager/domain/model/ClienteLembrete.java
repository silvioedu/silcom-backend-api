package com.silcom.manager.domain.model;

import java.time.LocalDate;
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

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tblclientelembrete")
public class ClienteLembrete {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")    
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_lembrete")
    private LembreteTipo lembreteTipo;
    
    private LocalDate dataEvento;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

}

package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tblclientedocumento")
public class ClienteDocumento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")    
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private DocumentoTipo documentoTipo;
    
    private String documento;
    
    private boolean isento;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

}
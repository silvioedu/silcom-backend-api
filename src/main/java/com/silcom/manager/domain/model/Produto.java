package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Data
@Entity
@Table(name = "tblproduto")
public class Produto {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private ProdutoTipo tipo;

    @ManyToOne
    @JoinColumn(name = "id_cor")
    private ProdutoCor cor;

    @ManyToOne
    @JoinColumn(name = "id_detalhe")
    private ProdutoDetalhe detalhe;

    @ManyToOne
    @JoinColumn(name = "id_complemento")
    private ProdutoComplemento complemento;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private ProdutoFabricante fabricante;

    private String codigo;

    private String folder;

    private BigDecimal preco;
    
    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;
    
    Produto () {
        // default constructor
    }

    public Produto(Long id, ProdutoTipo tipo, ProdutoCor cor, ProdutoDetalhe detalhe, ProdutoComplemento complemento, ProdutoFabricante fabricante, String codigo, String folder, BigDecimal preco, OffsetDateTime dataCriacao, OffsetDateTime dataAtualizacao) {
        this.id = id;
        this.tipo = tipo;
        this.cor = cor;
        this.detalhe = detalhe;
        this.complemento = complemento;
        this.fabricante = fabricante;
        this.codigo = codigo;
        this.folder = folder;
        this.preco = preco;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    
    public void geraCodigo() {
        this.codigo = this.tipo.getSigla()
            .concat(this.cor.getSigla())
            .concat(this.detalhe.getSigla())
            .concat(this.complemento.getSigla())
            .concat(this.fabricante.getSigla());
    }

    public void format() {
        this.codigo = this.codigo.toUpperCase();
        this.folder = this.folder.toUpperCase();
    }

}

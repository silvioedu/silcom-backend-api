package com.silcom.manager.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
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
@Table(name = "tblclientevendaitem")
public class ClienteVendaItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente_venda")    
    private ClienteVenda clienteVenda;

    @ManyToOne
    @JoinColumn(name = "id_produto")    
    private Produto produto;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;    

    ClienteVendaItem() {
        // default constructor
    }

    public ClienteVendaItem(Long id, ClienteVenda clienteVenda, Produto produto, Integer quantidade, BigDecimal valorUnitario, OffsetDateTime dataCriacao, OffsetDateTime dataAtualizacao) {
        this.id = id;
        this.clienteVenda = clienteVenda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

}

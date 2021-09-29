package com.silcom.manager.domain.model;

import java.text.ParseException;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.text.MaskFormatter;

import com.silcom.manager.domain.enums.MaskEnum;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tblclienteendereco")
public class ClienteEndereco {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")    
    private Cliente cliente;

    private String cep;

    private String logradouro;

    private String numero;

	private String complemento;

    private String bairro;

	private String cidade;

    private String estado;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    public void format() {
        this.estado = this.estado.toUpperCase();

        this.cep = this.cep.replaceAll("[^\\d]", "");
        try {
            MaskFormatter mask;
            mask = new MaskFormatter(MaskEnum.CEP.getFormat());
            int digitos = MaskEnum.CEP.getDigits();

            if(this.cep.length() > 0) {
                String zeros ="%0"+digitos+"d";
                this.cep = String.format(zeros, Long.valueOf(this.cep));
                mask.setValueContainsLiteralCharacters(false);
                this.cep = mask.valueToString(this.cep);
            }
        } catch (ParseException e) {
            this.cep = this.cep.replaceAll("[^\\d]", "");
        }
    }

}

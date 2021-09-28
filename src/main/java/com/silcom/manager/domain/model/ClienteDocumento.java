package com.silcom.manager.domain.model;

import java.text.ParseException;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tblclientedocumento")
public class ClienteDocumento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
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

    public void format(){

        this.documento = this.documento.replaceAll("[^\\d]", "");
        try {
            MaskFormatter mask;
            int digitos = 0;

            switch (this.documentoTipo.getId().intValue()) {
                case 1:
                    mask = new MaskFormatter(MaskEnum.CPF.getFormat());
                    digitos = MaskEnum.CPF.getDigits();
                    break;
                case 2:
                    mask = new MaskFormatter(MaskEnum.CNPJ.getFormat());
                    digitos = MaskEnum.CNPJ.getDigits();
                    break;
                case 3:
                    mask = new MaskFormatter(MaskEnum.IE.getFormat());
                    digitos = MaskEnum.IE.getDigits();
                    break;
                default:
                    mask = new MaskFormatter();
            }

            if(this.documento.length() > 0) {
                String zeros ="%0"+digitos+"d";
                this.documento = String.format(zeros, Long.valueOf(this.documento));
                mask.setValueContainsLiteralCharacters(false);
                this.documento = mask.valueToString(this.documento);
            }
        } catch (ParseException e) {
            this.documento = this.documento.replaceAll("[^\\d]", "");
        }
    }

}

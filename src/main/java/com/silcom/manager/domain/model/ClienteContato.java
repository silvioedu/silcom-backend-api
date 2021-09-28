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
@Table(name = "tblclientecontato")
public class ClienteContato {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")    
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contato")
    private ContatoTipo contatoTipo;
    
    private String contato;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    public void format(){
        this.contato = this.contato.replaceAll("[^\\d]", "");
        try {
            MaskFormatter mask;

            switch (this.contatoTipo.getId().intValue()) {
                case 1:
                    mask = new MaskFormatter(MaskEnum.TELEFONE.getFormat());

                    if (this.contato.length() == MaskEnum.TELEFONE.getDigits()-2) {
                        this.contato = "11".concat(this.contato);
                    }
                    break;
                case 2:
                    mask = new MaskFormatter(MaskEnum.TELEFONE_CELULAR.getFormat());

                    if (this.contato.length() == MaskEnum.TELEFONE_CELULAR.getDigits()-2) {
                        this.contato = "11".concat(this.contato);
                    }
                    break;
                default:
                    mask = new MaskFormatter();
            }

            if(this.contato.length() > 0) {
                mask.setValueContainsLiteralCharacters(false);
                this.contato = mask.valueToString(this.contato);
            }
        } catch (ParseException e) {
            this.contato = this.contato.replaceAll("[^\\d]", "");
        }
    }

}

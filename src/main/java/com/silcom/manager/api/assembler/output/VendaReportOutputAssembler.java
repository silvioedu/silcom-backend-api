package com.silcom.manager.api.assembler.output;

import java.util.List;
import java.util.stream.Collectors;

import com.silcom.manager.api.dto.output.report.VendaItemReportDTO;
import com.silcom.manager.api.dto.output.report.VendaReportDTO;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.ClienteVendaItem;

import org.springframework.stereotype.Component;

@Component
public class VendaReportOutputAssembler {
    
    private static final String NOT_FOUND = "Não cadastrado";

    public VendaReportDTO toDTO(ClienteVenda clienteVenda, List<ClienteVendaItem> itensVenda,
        List<ClienteDocumento> clienteDocumentos, List<ClienteContato> clienteContatos, List<ClienteEndereco> clienteEnderecos) {

        return VendaReportDTO.builder()
            .id(clienteVenda.getId())
            .clienteRazaoSocial(clienteVenda.getCliente().getRazaoSocial())
            .status(clienteVenda.getStatus())
            .formaPagamento(clienteVenda.getFormaPagamentoTipo().getNome())
            .valorTotal(clienteVenda.getValorTotal())
            .emitirNota(clienteVenda.isEmitirNota())
            .observacoes(clienteVenda.getObservacoes())
            .dataCriacao(clienteVenda.getDataCriacao())
            .itens(this.getVendaItensDTO(itensVenda))
            .documento(this.getDocumento(clienteDocumentos))
            .email(this.getEmail(clienteContatos))
            .telefone(this.getTelefone(clienteContatos))
            .endereco(this.getEndereco(clienteEnderecos))
            .build();
    }

    private String getEndereco(List<ClienteEndereco> clienteEnderecos) {
        return clienteEnderecos.stream()
            .filter(end -> end.getEnderecoTipo().getId().equals(2L))
            .findFirst()
            .map(ClienteEndereco::getDescription)
            .orElse(this.getEnderecoAlternativo(clienteEnderecos));
    }

    private String getEnderecoAlternativo(List<ClienteEndereco> clienteEnderecos) {
        return clienteEnderecos.stream()
            .findAny()
            .map(ClienteEndereco::getDescription)
            .orElse(NOT_FOUND);
    }

    private List<VendaItemReportDTO> getVendaItensDTO(List<ClienteVendaItem> itensVenda) {
        return itensVenda.stream()
            .map(item -> 
                VendaItemReportDTO.builder()
                    .codigo(item.getProduto().getCodigo())
                    .descricao(item.getProduto().getDescription())
                    .tamanho(item.getTamanho())
                    .quantidade(item.getQuantidade())
                    .valorUnitario(item.getValorUnitario())
                    .build()
            )
            .collect(Collectors.toList());
    }

    private String getDocumento(List<ClienteDocumento> clienteDocumentos) {        
        String documento = clienteDocumentos.stream()
            .map(doc -> String.format("%s: %s", 
                doc.getDocumentoTipo().getNome(),
                doc.getDocumento().length() > 0 ? 
                    doc.getDocumento() : doc.getIsentoDescription())
            )
            .reduce("", (partial, doc) -> partial.concat(", ").concat(doc));
        
            return documento.isEmpty() ? NOT_FOUND : documento.substring(2);

    }

    private String getEmail(List<ClienteContato> clienteContatos) {
        return clienteContatos.stream()
            .filter(ctt -> ctt.getContatoTipo().getId().equals(3L))
            .findFirst()
            .map(ClienteContato::getContato)
            .orElse(NOT_FOUND); 
    }

    private String getTelefone(List<ClienteContato> clienteContatos) {
        String telefone =  clienteContatos.stream()
            .filter(ctt -> ctt.getContatoTipo().getId().equals(1L) || ctt.getContatoTipo().getId().equals(2L))
            .map(ClienteContato::getContato)
            .reduce("", (partial, ctt) -> partial.concat(", ").concat(ctt));

        return telefone.isEmpty() ? NOT_FOUND : telefone.substring(2);
    }


}

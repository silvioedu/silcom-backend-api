package com.silcom.manager.domain.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import com.silcom.manager.api.assembler.output.VendaReportOutputAssembler;
import com.silcom.manager.api.dto.output.report.VendaReportDTO;
import com.silcom.manager.domain.exception.ReportException;
import com.silcom.manager.domain.model.ClienteContato;
import com.silcom.manager.domain.model.ClienteDocumento;
import com.silcom.manager.domain.model.ClienteEndereco;
import com.silcom.manager.domain.model.ClienteVenda;
import com.silcom.manager.domain.model.ClienteVendaItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
    
    @Autowired
    private ClienteVendaService vendaService;

    @Autowired
    private ClienteVendaItemService vendaItemService;

    @Autowired
    private ClienteDocumentoService documentoService;

    @Autowired
    private ClienteContatoService contatoService;

    @Autowired
    private ClienteEnderecoService enderecoService;

    @Autowired
    private VendaReportOutputAssembler vendaReportOutputAssembler;

    public VendaReportDTO getVenda(Long id) {
        final ClienteVenda clienteVenda = vendaService.findByVendaId(id);

        if(Objects.isNull(clienteVenda.getCliente()) || Objects.isNull(clienteVenda.getCliente().getId())) {
            throw new ReportException("Problemas ao gerar o relatório, venda não possui cliente");
        }

        final Long clienteId = clienteVenda.getCliente().getId();
        final List<ClienteVendaItem> itensVenda = vendaItemService.findAllByVenda(id);
        final List<ClienteDocumento> clienteDocumentos = documentoService.findAll(clienteId);
        final List<ClienteContato> clienteContatos = contatoService.findAll(clienteId);
        final List<ClienteEndereco> clienteEnderecos = enderecoService.findAll(clienteId);

        return vendaReportOutputAssembler.toDTO(clienteVenda, itensVenda, clienteDocumentos, clienteContatos, clienteEnderecos);
    }

    public byte[] getVendaPDF(Long id) {
        try {

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            List<VendaReportDTO> vendas = new ArrayList<>();
            vendas.add(this.getVenda(id));
            
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendas);
            InputStream inputStream = this.getClass().getResourceAsStream("/reports/venda-report.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);
		
			return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new ReportException("Deu erro na geração do PDF "+ e);
        }

    }


}

package com.silcom.manager.api.controller;

import com.silcom.manager.api.dto.output.report.VendaReportDTO;
import com.silcom.manager.domain.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    @GetMapping("/vendas/{id}")
    public VendaReportDTO getVenda(@PathVariable(required = true) Long id) {
        return reportService.getVenda(id);
    }

    @GetMapping(path = "/vendas/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getVendaPDF(@PathVariable(required = true) Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pedido.pdf");
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .headers(headers)
            .body(reportService.getVendaPDF(id));
    }

}
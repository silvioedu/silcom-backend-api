package com.silcom.manager.domain.service;

import java.util.HashMap;
import java.util.Map;

import com.silcom.manager.domain.exception.RestTemplateException;
import com.silcom.manager.domain.model.Cep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultaCepService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.consultacep.endpoint}")
    private String URL;

    public Cep find(String cep) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("cepId", cep);

        try{
            return restTemplate.getForObject(URL, Cep.class, uriVariables);
        } catch(HttpClientErrorException e) {
            System.out.println(e.getMessage());
            throw new RestTemplateException(
                String.format("Ocorreu um erro %d ao consultar o cep", e.getStatusCode().value())
            );
        }
    }

}

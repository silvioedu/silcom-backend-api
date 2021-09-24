package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.RamoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class RamoMock {
    
    public static Ramo getInstance() {
        Ramo ramo = new Ramo();
        ramo.setId(RandomUtils.nextLong(1, 24));
        ramo.setNome("Nome Ramo " + ramo.getId());
        ramo.setDataCriacao(OffsetDateTime.now());

        return ramo;
    }

    public static RamoInputDTO getInputInstance() {
        Ramo ramo = getInstance();
        RamoInputDTO input = new RamoInputDTO();
        input.setNome(ramo.getNome());
        return input;
    }

}

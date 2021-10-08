package com.silcom.manager.domain.model;

import java.time.OffsetDateTime;

import com.silcom.manager.api.dto.input.LembreteTipoInputDTO;

import org.apache.commons.lang3.RandomUtils;

public class LembreteTipoMock {
    
    public static LembreteTipo getInstance() {
        LembreteTipo lembreteTipo = new LembreteTipo();
        lembreteTipo.setId(RandomUtils.nextLong(1, 24));
        lembreteTipo.setNome("Lembrete Tp " + lembreteTipo.getId());
        lembreteTipo.setIntervalo(RandomUtils.nextInt(1, 99));
        lembreteTipo.setDataCriacao(OffsetDateTime.now());

        return lembreteTipo;
    }

    public static LembreteTipoInputDTO getInputInstance() {
        LembreteTipo lembreteTipo = getInstance();
        LembreteTipoInputDTO input = new LembreteTipoInputDTO();
        input.setNome(lembreteTipo.getNome());
        input.setIntervalo(lembreteTipo.getIntervalo());
        return input;
    }

    public static LembreteTipo getRealInstance() {
        LembreteTipo lembreteTipo = new LembreteTipo();
        lembreteTipo.setId(1L);
        lembreteTipo.setNome("Próximo contato");
        lembreteTipo.setIntervalo(7);
        return lembreteTipo;
    }
}

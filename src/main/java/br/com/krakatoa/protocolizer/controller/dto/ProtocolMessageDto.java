package br.com.krakatoa.protocolizer.controller.dto;

import lombok.Getter;

@Getter
public class ProtocolMessageDto {

    private final String raw;

    public ProtocolMessageDto(String raw) {
        this.raw = raw;
    }
}

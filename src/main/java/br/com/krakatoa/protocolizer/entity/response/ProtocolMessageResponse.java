package br.com.krakatoa.protocolizer.entity.response;

import lombok.Getter;

@Getter
public class ProtocolMessageResponse {

    private final String raw;

    public ProtocolMessageResponse(String raw) {
        this.raw = raw;
    }
}

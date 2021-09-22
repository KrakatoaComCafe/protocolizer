package br.com.krakatoa.protocolizer.entity.response.message.field;

import lombok.Getter;

@Getter
public class FixedResponse implements FieldResponse {

    private final String name;
    private final String value;

    public FixedResponse(String name, String value) {
        this.name = name;
        this.value = value;
    }

}

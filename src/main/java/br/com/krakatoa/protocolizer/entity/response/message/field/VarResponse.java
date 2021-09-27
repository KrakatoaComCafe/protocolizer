package br.com.krakatoa.protocolizer.entity.response.message.field;

import lombok.Getter;

@Getter
public class VarResponse implements FieldResponse {

    private final String name;
    private final String length;
    private final String value;

    public VarResponse(String name, String length, String value) {
        this.name = name;
        this.length = length;
        this.value = value;
    }


    @Override
    public int getFieldLength() {
        return this.length.length() + this.value.length();
    }
}

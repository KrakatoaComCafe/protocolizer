package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.model.Field;
import br.com.krakatoa.protocolizer.model.Protocol;

public class FieldForm {

    private String name;
    private int maxLength;
    private int minLength;

    public FieldForm(String name, int minLength, int maxLength) {
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public Field convertToField(Protocol protocol) {
        return new Field(this.name, this.minLength, this.maxLength, protocol);
    }
}

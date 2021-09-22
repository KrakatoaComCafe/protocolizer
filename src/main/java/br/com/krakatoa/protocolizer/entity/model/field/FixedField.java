package br.com.krakatoa.protocolizer.entity.model.field;

import br.com.krakatoa.protocolizer.format.FieldType;

class FixedField implements Field {

    private final String name;
    private final int length;

    public FixedField(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.FIXED;
    }
}

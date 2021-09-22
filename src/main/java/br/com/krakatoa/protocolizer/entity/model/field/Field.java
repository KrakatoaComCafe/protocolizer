package br.com.krakatoa.protocolizer.entity.model.field;

import br.com.krakatoa.protocolizer.format.FieldType;

public interface Field {

    String getName();

    int getLength();

    FieldType getFieldType();
}

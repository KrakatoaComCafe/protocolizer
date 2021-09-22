package br.com.krakatoa.protocolizer.entity.model.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class VarField implements Field{

    private final String name;
    private final int vli;
    private final int length;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLength() {
        return this.vli + this.length;
    }

    @Override
    public FieldType getFieldType() {
        switch (this.vli) {
            case 2:
                return FieldType.LLVAR;
            case 3:
                return FieldType.LLLVAR;
            case 4:
                return FieldType.LLLLVAR;
            default:
                throw new VarFieldException("Unexpected value [" + this.vli + "]");
        }
    }
}

class VarFieldException extends RuntimeException {

    public VarFieldException(String message) {
        super(message);
    }
}
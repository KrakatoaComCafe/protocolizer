package br.com.krakatoa.protocolizer.format;

public enum FieldType {
    FIXED,
    LLVAR,
    LLLVAR,
    LLLLVAR;


    public static FieldType getByClassName() {
        return FIXED;
    }
}

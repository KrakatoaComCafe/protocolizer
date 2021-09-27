package br.com.krakatoa.protocolizer.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum FieldType {
    FIXED,
    LLVAR,
    LLLVAR,
    LLLLVAR;

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldType.class);

    public static int getVliFromVarField(FieldType fieldType) {
        switch (fieldType) {
            case LLVAR:
                return 2;
            case LLLVAR:
                return 3;
            case LLLLVAR:
                return 4;
            default:
                LOGGER.error("Unsupported value used [{}]", fieldType);
                return 0;
        }
    }
}

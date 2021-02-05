package br.com.krakatoa.protocolizer.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormValidationErrorDto {

    private final String field;
    private final String error;

}

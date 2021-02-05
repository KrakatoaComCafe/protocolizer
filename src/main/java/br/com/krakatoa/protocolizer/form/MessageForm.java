package br.com.krakatoa.protocolizer.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class MessageForm {

    @NotNull
    @NotEmpty
    private String protocol;
    @NotNull
    @NotEmpty
    private String version;
    private String encoding;
    @NotNull
    @NotEmpty
    private String bitmap;
    @NotNull
    @NotEmpty
    private String raw;
}

package br.com.krakatoa.protocolizer.entity.form;

import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class MessageForm {

    @NotNull
    @NotEmpty
    private final String protocol;
    @NotNull
    @NotEmpty
    private final String version;
    private final Encoding encoding;
    @NotNull
    @NotEmpty
    private final String bitmap;
    @NotNull
    @NotEmpty
    private final String rawData;
}

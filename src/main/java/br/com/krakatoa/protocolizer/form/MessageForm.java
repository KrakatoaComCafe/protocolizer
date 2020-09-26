package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.format.Format;
import br.com.krakatoa.protocolizer.model.ProtocolMessage;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class MessageForm {

    @NotNull
    @NotEmpty
    private String raw;
    private String terminal;
    private String merchant;
    private String bitmap;
    private String originalTerminal;
    private String originalMerchant;
    private String originalBitmap;
    private Format format;

    public ProtocolMessage toMessage() {
        return new ProtocolMessage(this);
    }
}

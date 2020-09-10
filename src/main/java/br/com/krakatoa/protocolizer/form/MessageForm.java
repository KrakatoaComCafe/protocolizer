package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.model.ProtocolMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Integer terminalStartPosition;
    private Integer merchantStartPosition;
    private Integer bitmapStartPosition;

    public ProtocolMessage toMessage() {
        return new ProtocolMessage(this);
    }
}

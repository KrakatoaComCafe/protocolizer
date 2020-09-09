package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.model.ProtocolMessage;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class MessageForm {

    @NotNull @NotEmpty
    private String raw;
    private String terminal;
    private String merchant;
    private Integer terminalStartPosition;
    private Integer merchantStartPosition;

    public MessageForm(String raw, String terminal, String merchant, Integer terminalStartPosition, Integer merchantStartPosition) {
        this.raw = raw;
        this.terminal = terminal;
        this.merchant = merchant;
        this.terminalStartPosition = terminalStartPosition;
        this.merchantStartPosition = merchantStartPosition;
    }

    public ProtocolMessage toMessage() {
        return new ProtocolMessage(this);
    }
}

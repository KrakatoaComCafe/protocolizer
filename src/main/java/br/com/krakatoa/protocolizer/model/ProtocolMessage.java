package br.com.krakatoa.protocolizer.model;

import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.form.MessageForm;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProtocolMessage {

    private String raw;
    private String terminal;
    private String merchant;
    private Integer terminalStartPosition;
    private Integer merchantStartPosition;

    public ProtocolMessage(MessageForm messageForm) {
        this.raw = messageForm.getRaw();
        this.terminal = messageForm.getTerminal();
        this.merchant = messageForm.getMerchant();
        this.terminalStartPosition = messageForm.getTerminalStartPosition();
        this.merchantStartPosition = messageForm.getMerchantStartPosition();
    }

    @Builder
    public ProtocolMessage(String raw, String terminal, String merchant, Integer terminalStartPosition, Integer merchantStartPosition) {
        this.raw = raw;
        this.terminal = terminal;
        this.merchant = merchant;
        this.terminalStartPosition = terminalStartPosition;
        this.merchantStartPosition = merchantStartPosition;
    }

    public String updateRaw() {
        StringBuilder sb = new StringBuilder(this.raw);

        int terminalFinalPosition = this.terminalStartPosition + this.terminal.length() -1;
        int merchantFinalPosition = this.merchantStartPosition + this.merchant.length() -1;

        sb.replace(this.terminalStartPosition-1, terminalFinalPosition, this.terminal);
        sb.replace(this.merchantStartPosition-1, merchantFinalPosition, this.merchant);
        this.raw = sb.toString();
        return this.raw;
    }

    public ProtocolMessageDto toDto() {
        return new ProtocolMessageDto(this.raw);
    }
}

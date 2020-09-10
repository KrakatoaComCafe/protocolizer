package br.com.krakatoa.protocolizer.model;

import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.form.MessageForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProtocolMessage {

    private String raw;
    private String bitmap;
    private String terminal;
    private String merchant;
    private Integer bitmapStartPosition;
    private Integer terminalStartPosition;
    private Integer merchantStartPosition;

    public ProtocolMessage(MessageForm messageForm) {
        this.raw = messageForm.getRaw();
        this.bitmap = messageForm.getBitmap();
        this.terminal = messageForm.getTerminal();
        this.merchant = messageForm.getMerchant();
        this.bitmapStartPosition = messageForm.getBitmapStartPosition();
        this.terminalStartPosition = messageForm.getTerminalStartPosition();
        this.merchantStartPosition = messageForm.getMerchantStartPosition();
    }

    public String updateRaw() {
        StringBuilder sb = new StringBuilder(this.raw);

        int bitmapFinalPosition = this.bitmapStartPosition + this.bitmap.length() - 1;
        int terminalFinalPosition = this.terminalStartPosition + this.terminal.length() - 1;
        int merchantFinalPosition = this.merchantStartPosition + this.merchant.length() - 1;

        sb.replace(this.bitmapStartPosition - 1, bitmapFinalPosition, this.bitmap);
        sb.replace(this.terminalStartPosition - 1, terminalFinalPosition, this.terminal);
        sb.replace(this.merchantStartPosition - 1, merchantFinalPosition, this.merchant);
        this.raw = sb.toString();
        return this.raw;
    }

    public ProtocolMessageDto toDto() {
        return new ProtocolMessageDto(this.raw);
    }
}

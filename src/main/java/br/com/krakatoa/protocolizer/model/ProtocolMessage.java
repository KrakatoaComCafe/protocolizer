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
    private String originalBitmap;
    private String originalTerminal;
    private String originalMerchant;

    public ProtocolMessage(MessageForm messageForm) {
        this.raw = messageForm.getRaw();
        this.bitmap = messageForm.getBitmap();
        this.terminal = messageForm.getTerminal();
        this.merchant = messageForm.getMerchant();
        this.originalBitmap = messageForm.getOriginalBitmap();
        this.originalTerminal = messageForm.getOriginalTerminal();
        this.originalMerchant = messageForm.getOriginalMerchant();
    }

    public String updateRaw() {
        StringBuilder sb = new StringBuilder(this.raw);

        int bitmapIndex = sb.indexOf(this.originalBitmap);
        int terminalIndex = sb.indexOf(this.originalTerminal);
        int merchantIndex = sb.indexOf(this.originalMerchant);

        sb.replace(bitmapIndex, bitmapIndex+this.bitmap.length(), this.bitmap);
        sb.replace(terminalIndex, terminalIndex+this.terminal.length(), this.terminal);
        sb.replace(merchantIndex, merchantIndex+this.merchant.length(), this.merchant);

        this.raw = sb.toString();
        return this.raw;
    }

    public ProtocolMessageDto toDto() {
        return new ProtocolMessageDto(this.raw);
    }
}

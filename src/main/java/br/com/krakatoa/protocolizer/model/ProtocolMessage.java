package br.com.krakatoa.protocolizer.model;

import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.format.MessageFormat;
import br.com.krakatoa.protocolizer.format.MessageFormatFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProtocolMessage {

    private String raw;
    private String bitmap;
    private String terminal;
    private String merchant;
    private String originalBitmap;
    private String originalTerminal;
    private String originalMerchant;
    private final MessageFormat messageFormat;

    public ProtocolMessage(MessageForm messageForm) {
        this.raw = messageForm.getRaw();
        this.bitmap = messageForm.getBitmap();
        this.terminal = messageForm.getTerminal();
        this.merchant = messageForm.getMerchant();
        this.originalBitmap = messageForm.getOriginalBitmap();
        this.originalTerminal = messageForm.getOriginalTerminal();
        this.originalMerchant = messageForm.getOriginalMerchant();
        this.messageFormat = MessageFormatFactory.get(messageForm.getFormat());
    }

    public String updateRaw() {
        String originalMessageTerminal = this.messageFormat.execute(this.originalTerminal);
        String originalMessageMerchant = this.messageFormat.execute(this.originalMerchant);

        String newMessageTerminal = this.messageFormat.execute(this.terminal);
        String newMessageMerchant = this.messageFormat.execute(this.merchant);

        this.raw = this.raw.replace(this.originalBitmap, this.bitmap);
        this.raw = this.raw.replace(originalMessageTerminal, newMessageTerminal);
        this.raw = this.raw.replace(originalMessageMerchant, newMessageMerchant);

        return this.raw;
    }

    public ProtocolMessageDto toDto() {
        return new ProtocolMessageDto(this.raw);
    }
}

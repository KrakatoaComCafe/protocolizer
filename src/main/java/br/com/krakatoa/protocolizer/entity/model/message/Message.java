package br.com.krakatoa.protocolizer.entity.model.message;

import br.com.krakatoa.protocolizer.entity.form.MessageForm;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Message {

    String getProtocol();
    String getVersion();
    Encoding getEncoding();
    String getBitmap();
    String getRawData();

    class Factory {
        public Message create(MessageForm messageForm) {
            return new MessageDefault(messageForm.getProtocol(), messageForm.getVersion(), messageForm.getEncoding(),
                messageForm.getBitmap(), messageForm.getRawData());
        }
    }

    @Getter
    @AllArgsConstructor
    class MessageDefault implements Message {

        private final String protocol;
        private final String version;
        private final Encoding encoding;
        private final String bitmap;
        private final String rawData;
    }
}

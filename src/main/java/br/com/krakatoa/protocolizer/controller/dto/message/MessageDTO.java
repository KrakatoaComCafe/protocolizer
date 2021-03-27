package br.com.krakatoa.protocolizer.controller.dto.message;

import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MessageDTO {

    private final String protocol;
    private final String version;
    private final Encoding encoding;
    private final String bitmap;
    private final List<FieldDTO> fields;

    public MessageDTO(MessageForm messageForm, List<FieldEntity> fieldEntityEntities) {
        this.protocol = messageForm.getProtocol();
        this.version = messageForm.getVersion();
        this.encoding = messageForm.getEncoding();
        this.bitmap = messageForm.getBitmap();
        this.fields = new ArrayList<>();
        this.insertFieldToList(messageForm.getRawData(), fieldEntityEntities);
    }

    private void insertFieldToList(String raw, List<FieldEntity> fieldEntityEntities) {
        int lastPos = 0;
        for (FieldEntity fieldEntity : fieldEntityEntities) {
            FieldDTO fieldDTO = new FieldDTO(fieldEntity.getName(), raw.substring(lastPos, lastPos + fieldEntity.getLength()));
            this.fields.add(fieldDTO);
            lastPos += fieldEntity.getLength();
        }
    }

    public List<String> getFieldNames() {
        return this.fields.stream()
                .map(FieldDTO::getName)
                .collect(Collectors.toList());
    }

    public List<String> getFieldValues() {
        return this.fields.stream()
                .map(FieldDTO::getValue)
                .collect(Collectors.toList());
    }
}

@Getter
class FieldDTO {
    private final String name;
    private final String value;

    public FieldDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

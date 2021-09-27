package br.com.krakatoa.protocolizer.entity.response.message;

import br.com.krakatoa.protocolizer.entity.model.field.Field;
import br.com.krakatoa.protocolizer.entity.model.message.Message;
import br.com.krakatoa.protocolizer.entity.response.message.field.FieldResponse;
import br.com.krakatoa.protocolizer.entity.response.message.field.FieldResponseFactory;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public interface MessageResponse {

    class Factory {

        public MessageResponse create(Message message, List<Field> fieldList) {
            List<FieldResponse> fieldResponseList = this.insertFieldToList(message.getRawData(), fieldList);

            return new MessageResponseDefault(message.getProtocol(), message.getVersion(), message.getEncoding(),
                message.getBitmap(), fieldResponseList);
        }

        private List<FieldResponse> insertFieldToList(String rawData, List<Field> fieldList) {
            List<FieldResponse> fields = new ArrayList<>();
            int lastPos = 0;
            for (Field field : fieldList) {

                FieldResponse fieldResponse = new FieldResponseFactory().create(field, rawData, lastPos);
                fields.add(fieldResponse);
                lastPos += fieldResponse.getFieldLength();
            }
            return fields;
        }
    }

    @Getter
    @AllArgsConstructor
    class MessageResponseDefault implements MessageResponse {

        private final String protocol;
        private final String version;
        private final Encoding encoding;
        private final String bitmap;
        private final List<FieldResponse> fields;

//    public List<String> getFieldNames() {
//        return this.fields.stream()
//                .map(FieldResponse::getName)
//                .collect(Collectors.toList());
//    }
//
//    public List<String> getFieldValues() {
//        return this.fields.stream()
//                .map(FieldResponse::getValue)
//                .collect(Collectors.toList());
//    }
    }
}
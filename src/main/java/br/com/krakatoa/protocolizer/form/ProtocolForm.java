package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProtocolForm {

    @NotNull
    @NotEmpty
    private final String name;
    @NotNull
    @NotEmpty
    private final String version;
    private final Encoding encoding;
    private final Map<String, FieldForm> fields;

    public ProtocolForm(String name, String version, Encoding encoding, Map<String, FieldForm> fields) {
        this.name = name;
        this.version = version;
        this.encoding = encoding;
        this.fields = fields;
    }

    public Protocol convertToProtocol() {
        Protocol protocol = new Protocol(this.name, this.version, this.encoding);
        List<Field> fieldList = this.fields.values()
                .stream()
                .map(fieldForm -> fieldForm.convertToField(protocol))
                .collect(Collectors.toList());

        protocol.setFields(fieldList);

        return protocol;
    }
}
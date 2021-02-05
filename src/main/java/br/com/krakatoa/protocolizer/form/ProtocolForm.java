package br.com.krakatoa.protocolizer.form;

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
    private String name;
    @NotNull
    @NotEmpty
    private String version;
    private String encoding;
    private Map<String, FieldForm> fields;

    public ProtocolForm(String name, String version, String encoding, Map<String, FieldForm> fields) {
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
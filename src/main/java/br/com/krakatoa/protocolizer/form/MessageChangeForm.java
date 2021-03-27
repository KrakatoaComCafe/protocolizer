package br.com.krakatoa.protocolizer.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.TreeMap;

@Getter
@AllArgsConstructor
public class MessageChangeForm {

    private String protocolName;
    private String protocolVersion;
    private String bitmap;
    private String rawData;
    private TreeMap<String, String> fields;
}

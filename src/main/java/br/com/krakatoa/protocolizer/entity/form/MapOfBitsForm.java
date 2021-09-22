package br.com.krakatoa.protocolizer.entity.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class MapOfBitsForm {

    private final Map<String, Boolean> bitmap;

    public MapOfBitsForm(Map<String, Boolean> bitmap) {
        this.bitmap = bitmap;
    }

}

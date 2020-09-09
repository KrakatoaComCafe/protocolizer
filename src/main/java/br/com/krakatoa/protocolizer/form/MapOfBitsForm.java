package br.com.krakatoa.protocolizer.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class MapOfBitsForm {

    private Map<String, Boolean> bitmap;

    public MapOfBitsForm() {
    }

    public MapOfBitsForm(Map<String, Boolean> bitmap) {
        this.bitmap = bitmap;
    }

}

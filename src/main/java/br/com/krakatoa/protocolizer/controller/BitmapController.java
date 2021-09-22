package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.entity.form.BitmapForm;
import br.com.krakatoa.protocolizer.entity.form.MapOfBitsForm;
import br.com.krakatoa.protocolizer.service.ConverterAction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/bitmap")
public class BitmapController {

    private final ConverterAction converterAction;

    public BitmapController(ConverterAction converterAction) {
        this.converterAction = converterAction;
    }

    @PostMapping("/tojson")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> toJson(@RequestBody BitmapForm bitmapForm) {
        String binary = this.converterAction.hexToBinary(bitmapForm.getBitmap());
        Map<String, Boolean> bitmap = this.converterAction.binaryToMap(binary);

        return ResponseEntity.ok(bitmap);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> generateBitmap(@RequestBody MapOfBitsForm mapOfBitsForm) {
        String binaryBitmap = this.converterAction.mapToBinary(mapOfBitsForm.getBitmap());
        String bitmap = this.converterAction.binaryToHex(binaryBitmap);

        return ResponseEntity.ok(bitmap);
    }
}

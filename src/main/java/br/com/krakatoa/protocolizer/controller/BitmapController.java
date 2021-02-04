package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.form.BitmapForm;
import br.com.krakatoa.protocolizer.form.MapOfBitsForm;
import br.com.krakatoa.protocolizer.service.ConverterService;
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

    private final ConverterService converterService;

    public BitmapController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @PostMapping("/tojson")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> toJson(@RequestBody BitmapForm bitmapForm) {
        String binary = this.converterService.hexToBinary(bitmapForm.getBitmap());
        Map<String, Boolean> bitmap = this.converterService.binaryToMap(binary);

        return ResponseEntity.ok(bitmap);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> generateBitmap(@RequestBody MapOfBitsForm mapOfBitsForm) {
        String binaryBitmap = this.converterService.mapToBinary(mapOfBitsForm.getBitmap());
        String bitmap = this.converterService.binaryToHex(binaryBitmap);

        return ResponseEntity.ok(bitmap);
    }
}

package cm.cryptoapiandscrapper.cryptoapi.controller;

import cm.cryptoapiandscrapper.cryptoapi.dto.*;
import cm.cryptoapiandscrapper.cryptoapi.service.DivergenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crypto/divergence")
public class DivergenceController {

    @Autowired
    DivergenceService service;

    @GetMapping("/getAllMACDBearish")
    private ResponseEntity<?> getAllMACDBearish() {
        ResponseEntity<GeneralResponse<List<MACDBearish>>> generalResponseResponseEntity = new ResponseEntity<>(service.getAllMACDBearish(), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/getAllMACDBullish")
    private ResponseEntity<?> getAllMACDBullish() {
        ResponseEntity<GeneralResponse<List<MACDBullish>>> generalResponseResponseEntity = new ResponseEntity<>(service.getAllMACDBullish(), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/getAllRSIBullish")
    private ResponseEntity<?> getAllRSIBullish() {
        ResponseEntity<GeneralResponse<List<RSIBullish>>> generalResponseResponseEntity = new ResponseEntity<>(service.getAllRSIBullish(), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/getAllRSIBearish")
    private ResponseEntity<?> getAllRSIBearish() {
        ResponseEntity<GeneralResponse<List<RSIBearish>>> generalResponseResponseEntity = new ResponseEntity<>(service.getAllRSIBearish(), HttpStatus.OK);
        return generalResponseResponseEntity;
    }
}

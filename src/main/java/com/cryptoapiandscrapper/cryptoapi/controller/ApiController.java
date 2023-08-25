package com.cryptoapiandscrapper.cryptoapi.controller;

import com.cryptoapiandscrapper.cryptoapi.dto.GeneralBody;
import com.cryptoapiandscrapper.cryptoapi.dto.GeneralResponse;
import com.cryptoapiandscrapper.cryptoapi.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/crypto/")
@CrossOrigin(origins = "*")
public class ApiController {

    @Autowired
    ApiService service;

    @GetMapping("/divergences/MACDBearish")
    public ResponseEntity<?> MACDBearish() {
        log.info("api received request for {}: start","MACDBearish");
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("macd_divergence_bearish"), HttpStatus.OK);
        log.info("api received request for {}: done","MACDBearish");
        return generalResponseResponseEntity;
    }

    @GetMapping("/divergences/MACDBullish")
    public ResponseEntity<?> MACDBullish() {
        log.info("api received request for {}: start","MACDBullish");
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("macd_divergence_bullish"), HttpStatus.OK);
        log.info("api received request for {}: done","MACDBullish");

        return generalResponseResponseEntity;
    }

    @GetMapping("/divergences/RSIBullish")
    public ResponseEntity<?> RSIBullish() {
        log.info("api received request for {}: start","RSIBullish");
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("rsi_divergence_bullish"), HttpStatus.OK);
        log.info("api received request for {}: done","RSIBullish");

        return generalResponseResponseEntity;
    }

    @GetMapping("/divergences/RSIBearish")
    public ResponseEntity<?> RSIBearish() {
        log.info("api received request for {}: start","RSIBearish");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("rsi_divergence_bearish"), HttpStatus.OK);
        log.info("api received request for {}: done","RSIBearish");

        return generalResponseResponseEntity;
    }

    @GetMapping("/movingAverages/goldenCross")
    public ResponseEntity<?> goldenCross() {
        log.info("api received request for {}: start","goldenCross");
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("golden_cross"), HttpStatus.OK);
        log.info("api received request for {}: done","goldenCross");

        return generalResponseResponseEntity;
    }

    @GetMapping("/levels/trendLineSupport")
    public ResponseEntity<?> trendLineSupport() {
        log.info("api received request for {}: start","trendLineSupport");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("trendline_support"), HttpStatus.OK);
        log.info("api received request for {}: done","trendLineSupport");

        return generalResponseResponseEntity;
    }


    @GetMapping("/candlesticks/doji")
    public ResponseEntity<?> doji() {
        log.info("api received request for {}: start","doji");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("doji"), HttpStatus.OK);
        log.info("api received request for {}: done","doji");

        return generalResponseResponseEntity;
    }

    @GetMapping("/candlesticks/hammer")
    public ResponseEntity<?> hammer() {
        log.info("api received request for {}: start","hammer");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("hammer"), HttpStatus.OK);
        log.info("api received request for {}: done","hammer");

        return generalResponseResponseEntity;
    }

    @GetMapping("/candlesticks/spinningTop")
    public ResponseEntity<?> spinningTop() {
        log.info("api received request for {}: start","spinningTop");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("spinning_top"), HttpStatus.OK);
        log.info("api received request for {}: done","spinningTop");

        return generalResponseResponseEntity;
    }

//    @GetMapping("/candlesticks/engulfingCandle")
//    public ResponseEntity<?> engulfingCandle() {
//        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
//                new ResponseEntity<>(service.getAll("Candlesticks", "engulfing_candle_daily"), HttpStatus.OK);
//        return generalResponseResponseEntity;
//    }
//
//    @GetMapping("/candlesticks/insideCandle")
//    public ResponseEntity<?> insideCandle() {
//        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
//                new ResponseEntity<>(service.getAll("Candlesticks", "inside_day_daily"), HttpStatus.OK);
//        return generalResponseResponseEntity;
//    }

    @GetMapping("/technicalIndicators/CCIBuyTrigger")
    public ResponseEntity<?> CCIBuyTrigger() {
        log.info("api received request for {}: start","CCIBuyTrigger");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("cci_buy"), HttpStatus.OK);
        log.info("api received request for {}: done","CCIBuyTrigger");

        return generalResponseResponseEntity;
    }

    @GetMapping("/technicalIndicators/CCISellTrigger")
    public ResponseEntity<?> CCISellTrigger() {
        log.info("api received request for {}: start","CCISellTrigger");

        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(ApiService.getMap().get("cci_sell"), HttpStatus.OK);
        log.info("api received request for {}: done","CCISellTrigger");

        return generalResponseResponseEntity;
    }


    @GetMapping("/firstList")
    public ResponseEntity<?> firstList() {
        log.info("api received request for {}: start","firstList");

        GeneralResponse<List<GeneralBody>> response = ApiService.getMap().get("firstList");
        log.info("api received request for {}: done","firstList");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/footerTable")
    public ResponseEntity<?> footerTable() {
        log.info("api received request for {}: start","footerTable");
        GeneralResponse<?> body = ApiService.getFooterMap().get("footerTable");
        log.info("api received request for {}: done","footerTable");
        return ResponseEntity.ok(body);

    }
}

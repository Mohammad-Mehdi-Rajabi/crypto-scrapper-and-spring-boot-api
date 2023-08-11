package com.cryptoapiandscrapper.cryptoapi.controller;

import com.cryptoapiandscrapper.cryptoapi.dto.GeneralBody;
import com.cryptoapiandscrapper.cryptoapi.dto.GeneralResponse;
import com.cryptoapiandscrapper.cryptoapi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crypto/")
public class ApiController {

    @Autowired
    ApiService service;

    @GetMapping("/divergences/MACDBearish")
    public ResponseEntity<?> MACDBearish() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Divergences","macd_divergence_bearish"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/divergences/MACDBullish")
    public ResponseEntity<?> MACDBullish() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Divergences","macd_divergence_bullish"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/divergences/RSIBullish")
    public ResponseEntity<?> RSIBullish() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Divergences","rsi_divergence_bullish"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/divergences/RSIBearish")
    public ResponseEntity<?> RSIBearish() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Divergences","rsi_divergence_bearish"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }
    @GetMapping("/movingAverages/goldenCross")
    public ResponseEntity<?> goldenCross() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Moving Averages","golden_cross"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/levels/trendLineSupport")
    public ResponseEntity<?> trendLineSupport() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Levels", "trendline_support"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }


    @GetMapping("/candlesticks/doji")
    public ResponseEntity<?> doji() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Candlesticks", "doji"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/candlesticks/hammer")
    public ResponseEntity<?> hammer() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Candlesticks", "hammer"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/candlesticks/spinningTop")
    public ResponseEntity<?> spinningTop() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Candlesticks", "spinning_top"), HttpStatus.OK);
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
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Technical Indicators", "cci_buy"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }

    @GetMapping("/technicalIndicators/CCISellTrigger")
    public ResponseEntity<?> CCISellTrigger() {
        ResponseEntity<GeneralResponse<List<GeneralBody>>> generalResponseResponseEntity =
                new ResponseEntity<>(service.getAll("Technical Indicators", "cci_sell"), HttpStatus.OK);
        return generalResponseResponseEntity;
    }
}

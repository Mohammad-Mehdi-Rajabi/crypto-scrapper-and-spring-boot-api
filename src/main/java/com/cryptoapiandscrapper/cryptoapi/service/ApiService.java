package com.cryptoapiandscrapper.cryptoapi.service;

import com.cryptoapiandscrapper.cryptoapi.dto.FooterDto;
import com.cryptoapiandscrapper.cryptoapi.dto.GeneralBody;
import com.cryptoapiandscrapper.cryptoapi.dto.GeneralResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ApiService {

    private static final WebDriver driver;
    private static final WebDriver driver1;
    @Getter
    private static Map<String, GeneralResponse<List<GeneralBody>>> map;

    @Getter
    private static Map<String, GeneralResponse<List<FooterDto>>> footerMap;


    private static final String url = "https://tradytics.com/crypto-scan";
    private static final String url_footer = "https://www.cryptometer.io/whale-trades";

    static {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //C:\Users\Administrator\Documents\crypto\chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Documents\\crypto\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().minimize();
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
        driver.get(url);
        driver1 = new ChromeDriver(options);
        driver1.manage().window().minimize();
        driver1.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
        driver1.get(url_footer);

        map = new ConcurrentHashMap<>();
        map.put("firstList", firstList());
        map.put("macd_divergence_bearish", getAll("Divergences", "macd_divergence_bearish"));
        map.put("rsi_divergence_bullish", getAll("Divergences", "rsi_divergence_bullish"));
        map.put("macd_divergence_bullish", getAll("Divergences", "macd_divergence_bullish"));
        map.put("rsi_divergence_bearish", getAll("Divergences", "rsi_divergence_bearish"));
        map.put("golden_cross", getAll("Moving Averages", "golden_cross"));
        map.put("trendline_support", getAll("Levels", "trendline_support"));
        map.put("doji", getAll("Candlesticks", "doji"));
        map.put("hammer", getAll("Candlesticks", "hammer"));
        map.put("spinning_top", getAll("Candlesticks", "spinning_top"));
        map.put("cci_buy", getAll("Technical Indicators", "cci_buy"));
        map.put("cci_sell", getAll("Technical Indicators", "cci_sell"));
        footerMap = new ConcurrentHashMap<>();
        footerMap.put("footetTable", footerTable());
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(480000);

                    Map<String, GeneralResponse<List<GeneralBody>>> list = new ConcurrentHashMap<>();
                    list.put("firstList", firstList());
                    Thread.sleep(6000);
                    list.put("macd_divergence_bearish", getAll("Divergences", "macd_divergence_bearish"));
                    Thread.sleep(6000);
                    list.put("rsi_divergence_bullish", getAll("Divergences", "rsi_divergence_bullish"));
                    Thread.sleep(6000);
                    list.put("macd_divergence_bullish", getAll("Divergences", "macd_divergence_bullish"));
                    Thread.sleep(6000);
                    list.put("rsi_divergence_bearish", getAll("Divergences", "rsi_divergence_bearish"));
                    Thread.sleep(6000);
                    list.put("golden_cross", getAll("Moving Averages", "golden_cross"));
                    Thread.sleep(6000);
                    list.put("trendline_support", getAll("Levels", "trendline_support"));
                    Thread.sleep(6000);
                    list.put("doji", getAll("Candlesticks", "doji"));
                    Thread.sleep(6000);
                    list.put("hammer", getAll("Candlesticks", "hammer"));
                    Thread.sleep(6000);
                    list.put("spinning_top", getAll("Candlesticks", "spinning_top"));
                    Thread.sleep(6000);
                    list.put("cci_buy", getAll("Technical Indicators", "cci_buy"));
                    Thread.sleep(6000);
                    list.put("cci_sell", getAll("Technical Indicators", "cci_sell"));
                    Thread.sleep(6000);
                    map = list;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(60000);
                    Map<String, GeneralResponse<List<FooterDto>>> ft = new ConcurrentHashMap<>();
                    ft.put("footetTable", footerTable());
                    footerMap = ft;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static GeneralResponse<List<GeneralBody>> getAll(String tabName, String buttonName) {
        log.info("method{} start for {} tab and {} option", "getAll", tabName, buttonName);
        try {
            List<WebElement> elements = driver.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains(tabName)) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver.findElement(By.id(buttonName));
            rsi_divergence_bullish.click();
            List<WebElement> td = driver.findElements(By.tagName("td"));
            GeneralBody generalBody;
            ArrayList<GeneralBody> generalBodies = new ArrayList<>();
            if (td.get(0).getText().equals("No matching records found")) {
                log.info("method{} start for {} tab and {} option: {}", "getAll", tabName, buttonName, "success and null");
                elements = driver.findElements(By.id("watchlist-filter-scany"));
                divergences = null;
                for (WebElement w : elements) {
                    if (w.getText().contains(tabName)) {
                        divergences = w;
                        break;
                    }
                }
                divergences.click();
                rsi_divergence_bullish = driver.findElement(By.id(buttonName));
                rsi_divergence_bullish.click();

                return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), generalBodies);
            }
            for (int i = 0; i < td.size(); i++) {
                generalBody = new GeneralBody();
                generalBody.setRank(td.get(i + 0).getText());
                generalBody.setName(td.get(i + 1).getText());
                generalBody.setSymbol(td.get(i + 2).getText());
                generalBody.setPrice(td.get(i + 3).getText());
                generalBody.setLast24H(td.get(i + 4).getText());
                generalBody.setLast7D(td.get(i + 5).getText());
                generalBody.setVol(td.get(i + 6).getText());
                generalBody.setMarketCap(td.get(i + 7).getText());
                generalBody.setCIRCSUPPLY(td.get(i + 8).getText());
                i += 9;
                generalBodies.add(generalBody);
            }
            elements = driver.findElements(By.id("watchlist-filter-scany"));
            divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains(tabName)) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            rsi_divergence_bullish = driver.findElement(By.id(buttonName));
            rsi_divergence_bullish.click();

            log.info("method{} start for {} tab and {} option: {}", "getAll", tabName, buttonName, "success");

            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), generalBodies);
        } catch (Exception e) {
            log.info("method{} start for {} tab and {} option: {}", "getAll", tabName, buttonName, "failed");
            List<WebElement> elements = driver.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains(tabName)) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver.findElement(By.id(buttonName));
            rsi_divergence_bullish.click();

            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }

    public static GeneralResponse<List<GeneralBody>> firstList() {
        log.info("method{} start", "firstList");
        try {
            List<WebElement> td = driver.findElements(By.tagName("td"));
            GeneralBody generalBody;
            ArrayList<GeneralBody> generalBodies = new ArrayList<>();
            for (int i = 0; i < td.size(); i++) {
                generalBody = new GeneralBody();
                generalBody.setRank(td.get(i + 0).getText());
                generalBody.setName(td.get(i + 1).getText());
                generalBody.setSymbol(td.get(i + 2).getText());
                generalBody.setPrice(td.get(i + 3).getText());
                generalBody.setLast24H(td.get(i + 4).getText());
                generalBody.setLast7D(td.get(i + 5).getText());
                generalBody.setVol(td.get(i + 6).getText());
                generalBody.setMarketCap(td.get(i + 7).getText());
                generalBody.setCIRCSUPPLY(td.get(i + 8).getText());
                i += 9;
                generalBodies.add(generalBody);
            }


            log.info("method{}: {}", "getAll", "success");
            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), generalBodies);
        } catch (Exception e) {
            log.info("method{}: {}", "getAll", "failed");
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }

    public static GeneralResponse<List<FooterDto>> footerTable() {
        log.info("method{} start", "footerTable");
        try {
            List<WebElement> td = driver1.findElements(By.id("coinList"));

            String text = td.get(0).getText();
            List<String> strings = Arrays.asList(text.split("\n"));

            FooterDto footerDto = null;
            List<FooterDto> footerDtos = new LinkedList<>();
            for (String s : strings) {
                footerDto = new FooterDto();
                String[] s1 = s.split(" ");
                footerDto.setRank(s1[0]);
                footerDto.setName(s1[1]);
                footerDto.setExchange(s1[2]);
                footerDto.setQuantity(s1[3] + " " + s1[4]);
                footerDto.setTotal(s1[5] + " " + s1[6]);
                footerDto.setSide(s1[7]);
                footerDto.setDateTime(s1[8] + " " + s1[9] + " ago");
                footerDtos.add(footerDto);
            }
            log.info("method{}: {}", "footerTable", "success");
            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), footerDtos);
        } catch (Exception e) {
            log.info("method{}: {}", "footerTable", "failed");
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }
}

package cm.cryptoapiandscrapper.cryptoapi.service;

import cm.cryptoapiandscrapper.cryptoapi.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DivergenceService {
    private static final WebDriver driver1;
    private static final WebDriver driver2;
    private static final WebDriver driver3;
    private static final WebDriver driver4;

    static {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Desktop\\chromedriver.exe");
        driver1 = new ChromeDriver(options);
        driver1.manage().window().minimize();
        driver1.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        driver1.get("https://tradytics.com/crypto-scan");
        driver2 = new ChromeDriver(options);
        driver2.manage().window().minimize();
        driver2.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        driver2.get("https://tradytics.com/crypto-scan");
        driver3 = new ChromeDriver(options);
        driver3.manage().window().minimize();
        driver3.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        driver3.get("https://tradytics.com/crypto-scan");
        driver4 = new ChromeDriver(options);
        driver4.manage().window().minimize();
        driver4.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        driver4.get("https://tradytics.com/crypto-scan");
    }

    public GeneralResponse<List<MACDBearish>> getAllMACDBearish() {
        log.info("method{} start", "get all getAllMACDBearish");
        try {
            List<WebElement> elements = driver1.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver1.findElement(By.id("macd_divergence_bearish"));
            rsi_divergence_bullish.click();
            List<WebElement> td = driver1.findElements(By.tagName("td"));
            //ArrayList<String> arrayList = new ArrayList<>();
            //td.stream().forEach(d -> arrayList.add(d.getText()));
            MACDBearish macdBearish;
            ArrayList<MACDBearish> macdBearishes = new ArrayList<>();
            if (td.get(0).getText().equals("No matching records found")) {
                log.info("method{} start: {}", "get all getAllMACDBearish", "success and null");
                elements = driver1.findElements(By.id("watchlist-filter-scany"));
                divergences = null;
                for (WebElement w : elements) {
                    if (w.getText().contains("Divergences")) {
                        divergences = w;
                        break;
                    }
                }
                divergences.click();
                rsi_divergence_bullish = driver1.findElement(By.id("macd_divergence_bearish"));
                rsi_divergence_bullish.click();
                return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
            }

            for (int i = 0; i < td.size(); i++) {
                macdBearish = new MACDBearish();
                macdBearish.setRank(td.get(i + 0).getText());
                macdBearish.setName(td.get(i + 1).getText());
                macdBearish.setSymbol(td.get(i + 2).getText());
                macdBearish.setPrice(td.get(i + 3).getText());
                macdBearish.setLast24H(td.get(i + 4).getText());
                macdBearish.setLast7D(td.get(i + 5).getText());
                macdBearish.setVol(td.get(i + 6).getText());
                macdBearish.setMarketCap(td.get(i + 7).getText());
                macdBearish.setCIRCSUPPLY(td.get(i + 8).getText());
                i += 9;
                macdBearishes.add(macdBearish);
            }
            elements = driver1.findElements(By.id("watchlist-filter-scany"));
            divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            rsi_divergence_bullish = driver1.findElement(By.id("macd_divergence_bearish"));
            rsi_divergence_bullish.click();

            log.info("method{} start: {}", "get all getAllMACDBearish", "success");
            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
        } catch (Exception e) {
            log.info("method{} start: {}", "get all getAllMACDBearish", "failed");
            List<WebElement> elements = driver1.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver1.findElement(By.id("macd_divergence_bearish"));
            rsi_divergence_bullish.click();
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }


    public GeneralResponse<List<MACDBullish>> getAllMACDBullish() {
        log.info("method{} start", "get all getAllMACDBullish");
        try {
            List<WebElement> elements = driver2.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver2.findElement(By.id("macd_divergence_bullish"));
            rsi_divergence_bullish.click();
            List<WebElement> td = driver2.findElements(By.tagName("td"));
            //ArrayList<String> arrayList = new ArrayList<>();
            //td.stream().forEach(d -> arrayList.add(d.getText()));
            MACDBullish macdBearish;
            ArrayList<MACDBullish> macdBearishes = new ArrayList<>();
            if (td.get(0).getText().equals("No matching records found")) {
                log.info("method{} start: {}", "get all getAllMACDBullish", "success and null");
                elements = driver2.findElements(By.id("watchlist-filter-scany"));
                divergences = null;
                for (WebElement w : elements) {
                    if (w.getText().contains("Divergences")) {
                        divergences = w;
                        break;
                    }
                }
                divergences.click();
                rsi_divergence_bullish = driver2.findElement(By.id("macd_divergence_bullish"));
                rsi_divergence_bullish.click();
                return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
            }
            for (int i = 0; i < td.size(); i++) {
                macdBearish = new MACDBullish();
                macdBearish.setRank(td.get(i + 0).getText());
                macdBearish.setName(td.get(i + 1).getText());
                macdBearish.setSymbol(td.get(i + 2).getText());
                macdBearish.setPrice(td.get(i + 3).getText());
                macdBearish.setLast24H(td.get(i + 4).getText());
                macdBearish.setLast7D(td.get(i + 5).getText());
                macdBearish.setVol(td.get(i + 6).getText());
                macdBearish.setMarketCap(td.get(i + 7).getText());
                macdBearish.setCIRCSUPPLY(td.get(i + 8).getText());
                i += 9;
                macdBearishes.add(macdBearish);
            }
            elements = driver2.findElements(By.id("watchlist-filter-scany"));
            divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            rsi_divergence_bullish = driver2.findElement(By.id("macd_divergence_bullish"));
            rsi_divergence_bullish.click();

            log.info("method{} start: {}", "get all getAllMACDBullish", "success");
            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
        } catch (Exception e) {
            log.info("method{} start: {}", "get all getAllMACDBullish", "failed");
            List<WebElement> elements = driver2.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver2.findElement(By.id("macd_divergence_bullish"));
            rsi_divergence_bullish.click();
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }


    public GeneralResponse<List<RSIBullish>> getAllRSIBullish() {
        log.info("method{} start", "get all getAllRSIBullish");
        try {
            List<WebElement> elements = driver3.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver3.findElement(By.id("rsi_divergence_bullish"));
            rsi_divergence_bullish.click();
            List<WebElement> td = driver3.findElements(By.tagName("td"));
            //ArrayList<String> arrayList = new ArrayList<>();
            //td.stream().forEach(d -> arrayList.add(d.getText()));
            RSIBullish macdBearish;
            ArrayList<RSIBullish> macdBearishes = new ArrayList<>();
            if (td.get(0).getText().equals("No matching records found")) {
                log.info("method{} start: {}", "get all getAllRSIBullish", "success and null");
                elements = driver3.findElements(By.id("watchlist-filter-scany"));
                divergences = null;
                for (WebElement w : elements) {
                    if (w.getText().contains("Divergences")) {
                        divergences = w;
                        break;
                    }
                }
                divergences.click();
                rsi_divergence_bullish = driver3.findElement(By.id("rsi_divergence_bullish"));
                rsi_divergence_bullish.click();
                return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
            }
            for (int i = 0; i < td.size(); i++) {
                macdBearish = new RSIBullish();
                macdBearish.setRank(td.get(i + 0).getText());
                macdBearish.setName(td.get(i + 1).getText());
                macdBearish.setSymbol(td.get(i + 2).getText());
                macdBearish.setPrice(td.get(i + 3).getText());
                macdBearish.setLast24H(td.get(i + 4).getText());
                macdBearish.setLast7D(td.get(i + 5).getText());
                macdBearish.setVol(td.get(i + 6).getText());
                macdBearish.setMarketCap(td.get(i + 7).getText());
                macdBearish.setCIRCSUPPLY(td.get(i + 8).getText());
                i += 9;
                macdBearishes.add(macdBearish);
            }
            elements = driver3.findElements(By.id("watchlist-filter-scany"));
            divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            rsi_divergence_bullish = driver3.findElement(By.id("rsi_divergence_bullish"));
            rsi_divergence_bullish.click();

            log.info("method{} start: {}", "get all getAllRSIBullish", "success");
            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
        } catch (Exception e) {
            log.info("method{} start: {}", "get all getAllRSIBullish", "failed");
            List<WebElement> elements = driver3.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver3.findElement(By.id("rsi_divergence_bullish"));
            rsi_divergence_bullish.click();
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }


    public GeneralResponse<List<RSIBearish>> getAllRSIBearish() {
        log.info("method{} start", "get all getAllRSIBearish");
        try {
            List<WebElement> elements = driver4.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver4.findElement(By.id("rsi_divergence_bearish"));
            rsi_divergence_bullish.click();
            List<WebElement> td = driver4.findElements(By.tagName("td"));
            //ArrayList<String> arrayList = new ArrayList<>();
            //td.stream().forEach(d -> arrayList.add(d.getText()));
            RSIBearish macdBearish;
            ArrayList<RSIBearish> macdBearishes = new ArrayList<>();
            if (td.get(0).getText().equals("No matching records found")) {
                log.info("method{} start: {}", "get all getAllRSIBearish", "success and null");
                elements = driver4.findElements(By.id("watchlist-filter-scany"));
                divergences = null;
                for (WebElement w : elements) {
                    if (w.getText().contains("Divergences")) {
                        divergences = w;
                        break;
                    }
                }
                divergences.click();
                rsi_divergence_bullish = driver4.findElement(By.id("rsi_divergence_bearish"));
                rsi_divergence_bullish.click();
                return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
            }
            for (int i = 0; i < td.size(); i++) {
                macdBearish = new RSIBearish();
                macdBearish.setRank(td.get(i + 0).getText());
                macdBearish.setName(td.get(i + 1).getText());
                macdBearish.setSymbol(td.get(i + 2).getText());
                macdBearish.setPrice(td.get(i + 3).getText());
                macdBearish.setLast24H(td.get(i + 4).getText());
                macdBearish.setLast7D(td.get(i + 5).getText());
                macdBearish.setVol(td.get(i + 6).getText());
                macdBearish.setMarketCap(td.get(i + 7).getText());
                macdBearish.setCIRCSUPPLY(td.get(i + 8).getText());
                i += 9;
                macdBearishes.add(macdBearish);
            }
            elements = driver4.findElements(By.id("watchlist-filter-scany"));
            divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            rsi_divergence_bullish = driver4.findElement(By.id("rsi_divergence_bearish"));
            rsi_divergence_bullish.click();

            log.info("method{} start: {}", "get all getAllRSIBearish", "success");
            return new GeneralResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), macdBearishes);
        } catch (Exception e) {
            log.info("method{} start: {}", "get all getAllRSIBearish", "failed");
            List<WebElement> elements = driver4.findElements(By.id("watchlist-filter-scany"));
            WebElement divergences = null;
            for (WebElement w : elements) {
                if (w.getText().contains("Divergences")) {
                    divergences = w;
                    break;
                }
            }
            divergences.click();
            WebElement rsi_divergence_bullish = driver4.findElement(By.id("rsi_divergence_bearish"));
            rsi_divergence_bullish.click();
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }
}

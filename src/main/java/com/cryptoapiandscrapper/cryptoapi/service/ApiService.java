package com.cryptoapiandscrapper.cryptoapi.service;

import com.cryptoapiandscrapper.cryptoapi.dto.GeneralBody;
import com.cryptoapiandscrapper.cryptoapi.dto.GeneralResponse;
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
public class ApiService {

    private static final WebDriver driver;

    private static final String url = "https://tradytics.com/crypto-scan";
static {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Desktop\\chromedriver.exe");
    driver = new ChromeDriver(options);
    driver.manage().window().minimize();
    driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
    driver.get(url);
}

    public GeneralResponse<List<GeneralBody>> getAll(String tabName, String buttonName) {
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
}
package com.liudmyla.zagumna.driver.manager.impl.chrome;

import com.liudmyla.zagumna.driver.manager.impl.base.BaseDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Chrome driver manager implementation.
 * It instantiates Chrome using chromedriver.exe from classpath
 *
 * @author lzahumna
 * @since 01/10/2017
 */
public class ChromeDriverManager extends BaseDriverManager {

    private static WebDriver chromeDriver = createChromeDriver();

    @Override
    protected WebDriver getDriver() {
        return chromeDriver;
    }

    private static WebDriver createChromeDriver() throws RuntimeException {
        URL chromeDriverResource = ChromeDriverManager.class.getClassLoader().getResource("chromedriver.exe");

        if (chromeDriverResource == null) {
            throw new RuntimeException("Chrome driver could not be found. Please check chromedriver.exe location.");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverResource.getPath());

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}

package com.liudmyla.zagumna.driver.manager.impl.firefox;

import com.liudmyla.zagumna.driver.manager.impl.base.BaseDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Firefox driver manager implementation.
 * Actually, it instantiates Firefox using geckodriver.exe from classpath
 *
 * @author lzahumna
 * @since 01/10/2017
 */
public class FirefoxDriverManager extends BaseDriverManager {

    private static WebDriver firefoxDriver = createFirefoxDriver();

    @Override
    protected WebDriver getDriver() {
        return firefoxDriver;
    }

    private static WebDriver createFirefoxDriver() throws RuntimeException {
        URL firefoxDriverResource = FirefoxDriverManager.class.getClassLoader().getResource("geckodriver.exe");

        if (firefoxDriverResource == null) {
            throw new RuntimeException("Firefox driver could not be found. Please check geckodriver.exe location.");
        }

        System.setProperty("webdriver.gecko.driver", firefoxDriverResource.getPath());

        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
package com.liudmyla.zagumna.driver.manager.impl.base;

import com.liudmyla.zagumna.driver.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDriverManager implements DriverManager {

    @Override
    public void openLink(String url) {
        getDriver().get(url);
    }

    @Override
    public void findElementAndClick(String elementIdentifier, ElementPathType type) {
        WebElement elementByType = findElementByType(elementIdentifier, type);
        elementByType.click();
    }

    @Override
    public void fillElementWithTextt(String text, String elementIdentifier, ElementPathType type) {
        WebElement elementByType = findElementByType(elementIdentifier, type);
        elementByType.sendKeys(text);
    }

    @Override
    public List<String> findRowAndCopyValues(String xpath) {
        List<WebElement> tdElements = getDriver().findElement(By.xpath(xpath)).findElements(By.tagName("td"));

        List<String> rowValues = new ArrayList<>();

        for (WebElement element : tdElements) {
            String innerHTML = element.getAttribute("innerHTML");
            rowValues.add(innerHTML);
        }
        return rowValues;
    }

    @Override
    public WebElement findElementByType(String elementIdentifier, ElementPathType type) {

        if (type == null) {
            throw new RuntimeException("Element pathType is null");
        }

        switch (type) {
            case ID:
                return getDriver().findElement(By.id(elementIdentifier));
            case CSS:
                return getDriver().findElement(By.cssSelector(elementIdentifier));
            case NAME:
                return getDriver().findElement(By.name(elementIdentifier));
            case XPATH:
                return getDriver().findElement(By.xpath(elementIdentifier));
            case LINK_TEXT:
                return getDriver().findElement(By.linkText(elementIdentifier));
            default:
                throw new RuntimeException("Type " + type + " is not found");
        }
    }

    @Override
    public void quit() {
        getDriver().quit();
    }

    protected abstract WebDriver getDriver();
}

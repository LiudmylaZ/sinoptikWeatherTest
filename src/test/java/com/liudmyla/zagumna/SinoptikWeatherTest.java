package com.liudmyla.zagumna;

import com.liudmyla.zagumna.driver.manager.DriverManager;
import com.liudmyla.zagumna.driver.manager.impl.chrome.ChromeDriverManager;
import com.liudmyla.zagumna.driver.manager.impl.firefox.FirefoxDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Run test suite with Firefox and Chrome.
 *
 * @author lzahumna
 * @since 03/10/2017
 */
@RunWith(Parameterized.class)
public class SinoptikWeatherTest {

    private static List<DriverManager> driverManagers = Arrays.asList(new ChromeDriverManager(), new FirefoxDriverManager());

    @Parameterized.Parameters
    public static Collection<DriverManager> data() {
        return driverManagers;
    }

    private DriverManager driverManager;

    public SinoptikWeatherTest(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Test
    public void pressureInRange() {
        // go to sinoptik
        driverManager.openLink("https://sinoptik.ua/");
        // enter text into search bar
        driverManager.fillElementWithTextt("Драгобрат", "search_city", DriverManager.ElementPathType.ID);
        // findElementAndClick on magnifying glass near search bar to start searching
        driverManager.findElementAndClick("//*[@id=\"form-search\"]/p[1]/input[2]", DriverManager.ElementPathType.XPATH);

        try {
            driverManager.findElementAndClick("Воскресенье", DriverManager.ElementPathType.LINK_TEXT);
        } catch (NoSuchElementException e) {
            // it means that element with link text is selected one. try to find selected link.
            WebElement elementByType = driverManager.findElementByType("loaded", DriverManager.ElementPathType.CSS);
            elementByType.click();
        } catch (StaleElementReferenceException e) {
            driverManager.findElementAndClick("Воскресенье", DriverManager.ElementPathType.LINK_TEXT);
        }

        List<String> rowValues = driverManager.findRowAndCopyValues("//*[@id=\"bd1c\"]/div[1]/div[2]/table/tbody/tr[5]");

        List<Integer> pressureList = convertToIntegers(rowValues);


        Assert.assertTrue("Not all values are in range.", validateWhetherPressureWithinRange(pressureList));
    }

    @Test
    public void humidityInRange() {
        // go to sinoptik
        driverManager.openLink("https://sinoptik.ua/");
        // enter text into search bar
        driverManager.fillElementWithTextt("Хмельницький", "search_city", DriverManager.ElementPathType.ID);
        // findElementAndClick on magnifying glass near search bar to start searching
        driverManager.findElementAndClick("//*[@id=\"form-search\"]/p[1]/input[2]", DriverManager.ElementPathType.XPATH);

        try {
            driverManager.findElementAndClick("Четверг", DriverManager.ElementPathType.LINK_TEXT);
        } catch (NoSuchElementException e) {
            // it means that element with link text is selected one. try to find selected link.
            WebElement elementByType = driverManager.findElementByType("loaded", DriverManager.ElementPathType.CSS);
            elementByType.click();
        } catch (StaleElementReferenceException e) {
            driverManager.findElementAndClick("Четверг", DriverManager.ElementPathType.LINK_TEXT);
        }

        List<String> rowValues = driverManager.findRowAndCopyValues("//*[@id=\"bd1c\"]/div[1]/div[2]/table/tbody/tr[6]");

        List<Integer> humidityList = convertToIntegers(rowValues);

        Assert.assertTrue("Not all values are in range.", validateWhetherHumidityWithinRange(humidityList));
    }

    @Test
    public void temperatureInRange() {
        // go to sinoptik
        driverManager.openLink("https://sinoptik.ua/");
        // enter text into search bar
        driverManager.fillElementWithTextt("Николаев", "search_city", DriverManager.ElementPathType.ID);
        // findElementAndClick on magnifying glass near search bar to start searching
        driverManager.findElementAndClick("//*[@id=\"form-search\"]/p[1]/input[2]", DriverManager.ElementPathType.XPATH);

        try {
            driverManager.findElementAndClick("Пятница", DriverManager.ElementPathType.LINK_TEXT);
        } catch (NoSuchElementException e) {
            // it means that element with link text is selected one. try to find selected link.
            WebElement elementByType = driverManager.findElementByType("loaded", DriverManager.ElementPathType.CSS);
            elementByType.click();
        } catch (StaleElementReferenceException e) {
            driverManager.findElementAndClick("Пятница", DriverManager.ElementPathType.LINK_TEXT);
        }

        List<String> rowValues = driverManager.findRowAndCopyValues("//*[@id=\"bd1c\"]/div[1]/div[2]/table/tbody/tr[3]");

        ArrayList<String> valuesWithoutDegrees = new ArrayList<>();

        for (String rowValue : rowValues) {
            valuesWithoutDegrees.add(rowValue.replace("°", ""));

        }

        List<Integer> temperatureList = convertToIntegers(valuesWithoutDegrees);

        Assert.assertTrue(validateWhetherTemperatureWithinRange(temperatureList));
    }

    private List<Integer> convertToIntegers(List<String> rowValues) {
        List<Integer> pressureList = new ArrayList<>();

        for (String rowValue : rowValues) {
            pressureList.add(Integer.parseInt(rowValue));
        }
        return pressureList;
    }

    private boolean validateWhetherPressureWithinRange(List<Integer> pressure) {
        boolean allWithinRange = true;

        for (Integer item : pressure) {
            allWithinRange = allWithinRange && between(item, 600, 700);
        }

        return allWithinRange;
    }

    private boolean validateWhetherHumidityWithinRange(List<Integer> humidity) {
        boolean allWithinRange = true;

        for (Integer item : humidity) {
            allWithinRange = allWithinRange && between(item, 30, 80);
        }

        return allWithinRange;
    }

    private boolean validateWhetherTemperatureWithinRange(List<Integer> humidity) {
        boolean allWithinRange = true;

        for (Integer item : humidity) {
            allWithinRange = allWithinRange && between(item, -20, +10);
        }

        return allWithinRange;
    }


    private static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        return (i >= minValueInclusive && i <= maxValueInclusive);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        for (DriverManager driverManager : driverManagers) {
            driverManager.quit();
        }
    }
}

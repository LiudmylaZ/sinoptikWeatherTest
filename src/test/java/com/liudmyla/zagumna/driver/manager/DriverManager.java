package com.liudmyla.zagumna.driver.manager;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Establish driver manager responsibilities and functions they can perform
 *
 * @author lzahumna
 * @since 01/10/2017
 */
public interface DriverManager {

    /**
     * Open {@param url}
     *
     * @param url site url
     */
    void openLink(String url);

    /**
     * Quit driver
     */
    void quit();

    /**
     * Find element by {@param elementIdentifier} of type {@param type} and click on it
     *
     * @param elementIdentifier string representation of element : id, name, css class, etc
     * @param type              identifier type {@linkplain ElementPathType}
     */
    void findElementAndClick(String elementIdentifier, ElementPathType type);


    /**
     * Find element by {@param elementIdentifier} of type {@param type} and enter {@param text} into it
     *
     * @param text              text to enter
     * @param elementIdentifier string representation of element : id, name, css class, etc
     * @param type              identifier type {@linkplain ElementPathType}
     */
    void fillElementWithTextt(String text, String elementIdentifier, ElementPathType type);

    /**
     * Find the table row by xPath and return all the <td/> values
     *
     * @param xpath path to the table tow
     * @return list of <td/> string values
     */
    List<String> findRowAndCopyValues(String xpath);

    /**
     * Find element by {@param elementIdentifier} of type {@param type}
     *
     * @param elementIdentifier string representation of element : id, name, css class, etc
     * @param type              identifier type {@linkplain ElementPathType}
     * @return {@linkplain WebElement}
     */
    WebElement findElementByType(String elementIdentifier, ElementPathType type);

    /**
     * Define the way how to find an element.
     * <p>
     * Element could be found by:
     * - css class
     * - id
     * - name
     * - text value
     * - xPath
     */
    enum ElementPathType {
        CSS,
        ID,
        NAME,
        XPATH,
        LINK_TEXT
    }

}

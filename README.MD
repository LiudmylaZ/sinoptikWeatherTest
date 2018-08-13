# Multi-browser parameterized Selenium test for Windows

Sample shows how to create browser-type-independent code structure to run the same test using multiple browsers avoiding code duplication.

While working with different browsers, you should always keep in mind slight differences between them, which could affect your tests. Selenium did a great job to help us not to spend time to implement the same methods for different browsers. However, it is the case if we talk about WebDriver instantiation. 

Driver manager structure introduced in this project help isolate init part of each WebDriver and use common functions to manipulate DOM objects.

To demonstrate, sinoptik.ua was taken as a sample and there were some dummy test cases implemented to give some intuition how it works   

### Prerequisites

- Java 8
- Maven
- Chrome/Firefox

### Running the tests

Clone the project and open in IDE. Find SinoptikWeatherTest, look through the code to check how it was done and run it.

## Authors

* **Liudmyla Zahumna** 
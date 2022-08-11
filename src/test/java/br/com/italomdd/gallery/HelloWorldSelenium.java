package br.com.italomdd.gallery;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {
    @Test
    public void helloWorldSelenium() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.quit();
    }
}
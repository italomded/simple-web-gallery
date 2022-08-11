package br.com.italomdd.gallery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private static final String INITIAL_URL = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void BeforeEach() {
        this.browser = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        this.browser.navigate().to(INITIAL_URL);
        this.browser.findElement(By.id("username")).sendKeys("italomdd");
        this.browser.findElement(By.id("password")).sendKeys("12345");
        this.browser.findElement(By.id("loginForm")).submit();
        assertFalse(this.browser.getCurrentUrl().contains(INITIAL_URL));
        assertTrue(this.browser.getPageSource().contains("Logout"));
    }

    @Test
    public void shouldNotLoginWithInvalidCredentials() {
        this.browser.navigate().to(INITIAL_URL);
        this.browser.findElement(By.id("username")).sendKeys("lorem");
        this.browser.findElement(By.id("password")).sendKeys("ipsum");
        this.browser.findElement(By.id("loginForm")).submit();
        assertTrue(this.browser.getCurrentUrl().contains(INITIAL_URL));
        assertTrue(this.browser.getPageSource().contains("Login"));
    }

    @Test
    public void shouldNotAccessThePageWithoutValidCredentials() {
        this.browser.navigate().to(INITIAL_URL);
        this.browser.findElement(By.id("username")).sendKeys("italomdd");
        this.browser.findElement(By.id("password")).sendKeys("12345");
        this.browser.findElement(By.id("loginForm")).submit();
        this.browser.findElement(By.id("logout")).click();
        this.browser.navigate().to("http://localhost:8080/uploads");
        assertTrue(this.browser.getCurrentUrl().contains(INITIAL_URL));
        assertTrue(this.browser.getPageSource().contains("Login"));
    }
}

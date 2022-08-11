package br.com.italomdd.gallery.login;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private static final String LOGIN_URL = "http://localhost:8080/login";
    private static final String UPLOAD_URL = "http://localhost:8080/login";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
    }

    public void closePage() {
        this.browser.quit();
    }

    public void navigateToLoginPage() {
        this.browser.navigate().to(LOGIN_URL);
    }

    public void navigateToUploadPage() {
        this.browser.navigate().to(UPLOAD_URL);
    }

    public void fulfillLoginForm(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public boolean isInLoginPage() {
        return browser.getCurrentUrl().contains(LOGIN_URL);
    }

    public boolean containsText(String text) {
        return browser.getPageSource().contains(text);
    }

    public boolean doLogout() {
        try {
            this.browser.findElement(By.id("logout")).click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean doLogin() {
        try {
            this.browser.findElement(By.id("loginForm")).submit();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

package br.com.italomdd.gallery.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class PageObject {
    protected WebDriver browser;

    public PageObject() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
    }

    public PageObject(WebDriver browser) {
        this.browser = browser;
    }

    public void closePage() {
        this.browser.quit();
    }

    public boolean isExpectedPage() {
        return browser.getCurrentUrl().contains(getExpectedPageUrl());
    }

    public boolean containsText(String text) {
        return browser.getPageSource().contains(text);
    }

    public boolean containsElementId(String elementId) {
        try {
            browser.findElement(By.id(elementId));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected abstract String getExpectedPageUrl();
}

package br.com.italomdd.gallery.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UploadFormPage extends PageObject {
    private final String PAGE_URL = "http://localhost:8080/submit";

    public UploadFormPage(WebDriver browser) {
        super(browser);
    }

    public HomePage fulfillUploadForm(String author, String title, String imageUrl, String font) {
        browser.findElement(By.id("author")).sendKeys(author);
        browser.findElement(By.id("title")).sendKeys(title);
        browser.findElement(By.id("url")).sendKeys(imageUrl);
        browser.findElement(By.id("font")).sendKeys(font);
        browser.findElement(By.id("form")).submit();
        return new HomePage(browser);
    }

    public boolean haveFormErrors() {
        try {
            WebElement submitForm = browser.findElement(By.id("form"));
            boolean error1 = submitForm.findElement(By.cssSelector(".mb-3:nth-child(1)"))
                    .findElement(By.className("invalid-feedback")).isDisplayed();
            boolean error2 = submitForm.findElement(By.cssSelector(".mb-3:nth-child(2)"))
                    .findElement(By.className("invalid-feedback")).isDisplayed();
            boolean error3 = submitForm.findElement(By.cssSelector(".mb-3:nth-child(3)"))
                    .findElement(By.className("invalid-feedback")).isDisplayed();
            boolean error4 = submitForm.findElement(By.cssSelector(".mb-3:nth-child(4)"))
                    .findElement(By.className("invalid-feedback")).isDisplayed();
            return error1 && error2 && error3 && error4;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    @Override
    protected String getExpectedPageUrl() {
        return PAGE_URL;
    }
}

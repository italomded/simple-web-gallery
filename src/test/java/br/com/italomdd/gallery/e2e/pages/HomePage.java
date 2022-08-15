package br.com.italomdd.gallery.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageObject {
    private final String PAGE_URL = "http://localhost:8080/home";

    public HomePage(WebDriver browser) {
        super(browser);
    }

    public UploadPage goToUploadPage() {
        browser.findElement(By.id("uploads")).click();
        return new UploadPage(browser);
    }

    @Override
    protected String getExpectedPageUrl() {
        return PAGE_URL;
    }

}

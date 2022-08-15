package br.com.italomdd.gallery.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadPage extends PageObject {
    private final String PAGE_URL = "http://localhost:8080/uploads";

    public UploadPage(WebDriver browser) {
        super(browser);
    }

    public UploadFormPage goToUploadFormPage() {
        browser.findElement(By.id("upload-form")).click();
        return new UploadFormPage(browser);
    }
    
    public boolean haveThisImage(String author, String title) {
        String pageSource = browser.getPageSource();
        boolean haveAuthor = pageSource.contains(author);
        boolean haveTitle = pageSource.contains(title);
        return haveAuthor && haveTitle;
    }

    @Override
    protected String getExpectedPageUrl() {
        return PAGE_URL;
    }
}

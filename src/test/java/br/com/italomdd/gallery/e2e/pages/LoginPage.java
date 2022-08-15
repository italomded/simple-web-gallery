package br.com.italomdd.gallery.e2e.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {
    private final String PAGE_URL = "http://localhost:8080/login";
    private final String UPLOAD_URL = "http://localhost:8080/uploads";
    
    public LoginPage() {
        super();
        this.browser.navigate().to(PAGE_URL);
    }

    public UploadPage navigateToUploadPage() {
        this.browser.navigate().to(UPLOAD_URL);
        return new UploadPage(this.browser);
    }

    public void fulfillLoginForm(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public boolean doLogout() {
        try {
            this.browser.findElement(By.id("logout")).click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public HomePage doLogin() {
        try {
            this.browser.findElement(By.id("loginForm")).submit();
            return new HomePage(this.browser);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    protected String getExpectedPageUrl() {
        return PAGE_URL;
    }
}

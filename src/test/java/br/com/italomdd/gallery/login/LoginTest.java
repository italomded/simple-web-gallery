package br.com.italomdd.gallery.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private LoginPage loginPage;

    @BeforeEach
    public void BeforeEach() {
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
    }

    @AfterEach
    public void afterEach() {
        loginPage.closePage();
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        
        loginPage.fulfillLoginForm("italomdd", "12345");
        loginPage.doLogin();
        assertFalse(loginPage.isInLoginPage());
        assertTrue(loginPage.containsText("Logout"));
    }

    @Test
    public void shouldNotLoginWithInvalidCredentials() {
        loginPage.fulfillLoginForm("lorem", "ipsum");
        loginPage.doLogin();
        assertTrue(loginPage.isInLoginPage());
        assertTrue(loginPage.containsText("Login"));
    }

    @Test
    public void shouldNotAccessThePageWithoutValidCredentials() {
        loginPage.fulfillLoginForm("italomdd", "12345");
        loginPage.doLogin();
        loginPage.doLogout();
        loginPage.navigateToUploadPage();
        assertTrue(loginPage.isInLoginPage());
        assertTrue(loginPage.containsText("Login"));
    }
}

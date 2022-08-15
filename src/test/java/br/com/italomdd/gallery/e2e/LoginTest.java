package br.com.italomdd.gallery.e2e;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.italomdd.gallery.e2e.pages.LoginPage;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "file:src/test/resources/data.sql")
public class LoginTest {
    

    private LoginPage loginPage;

    @BeforeEach
    public void BeforeEach() {
        loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        loginPage.closePage();
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        loginPage.fulfillLoginForm("italomdd", "12345");
        loginPage.doLogin();
        assertFalse(loginPage.isExpectedPage());
        assertTrue(loginPage.containsElementId("logout"));
    }

    @Test
    public void shouldNotLoginWithInvalidCredentials() {
        loginPage.fulfillLoginForm("lorem", "ipsum");
        loginPage.doLogin();
        assertTrue(loginPage.isExpectedPage());
        assertTrue(loginPage.containsElementId("login"));
        assertFalse(loginPage.containsElementId("logout"));
    }

    @Test
    public void shouldNotAccessThePageWithoutValidCredentials() {
        loginPage.fulfillLoginForm("italomdd", "12345");
        loginPage.doLogin();
        loginPage.doLogout();
        loginPage.navigateToUploadPage();
        assertTrue(loginPage.isExpectedPage());
        assertTrue(loginPage.containsElementId("login"));
    }
}

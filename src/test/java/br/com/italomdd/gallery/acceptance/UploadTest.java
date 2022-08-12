package br.com.italomdd.gallery.acceptance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import br.com.italomdd.gallery.acceptance.pages.HomePage;
import br.com.italomdd.gallery.acceptance.pages.LoginPage;
import br.com.italomdd.gallery.acceptance.pages.UploadFormPage;
import br.com.italomdd.gallery.acceptance.pages.UploadPage;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "file:src/test/resources/data.sql")
public class UploadTest {
    private UploadFormPage uploadFormPage;

    @BeforeEach
    public void BeforeEach() {
        LoginPage loginPage = new LoginPage();
        loginPage.fulfillLoginForm("italomdd", "12345");
        HomePage homePage = loginPage.doLogin();
        UploadPage uploadPage = homePage.goToUploadPage();
        this.uploadFormPage = uploadPage.goToUploadFormPage();
    }

    @AfterEach
    public void afterEach() {
        uploadFormPage.closePage();
    }

    @Test
    public void shouldRegisterNewImageWithValidData() {
        String formData = "Lorem ipsum";
        HomePage homePage = uploadFormPage.fulfillUploadForm(formData, formData, formData, formData);
        assertTrue(homePage.isExpectedPage());
        UploadPage uploadPage = homePage.goToUploadPage();
        assertTrue(uploadPage.haveThisImage(formData, formData));
    }

    @Test
    public void shouldNotRegisterImageWithBlankFields() {
        uploadFormPage.fulfillUploadForm("", "", "", "");
        assertTrue(uploadFormPage.isExpectedPage());
        assertTrue(uploadFormPage.haveFormErrors());
    }

}

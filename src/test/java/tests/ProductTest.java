package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.LoginPage;
import pages.ProductPage;

import static core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    ProductPage produtoPage = new ProductPage();

    @Test
    public void t01_deveAbrirMenu() {
        loginPage.loginComoStandardUser();
        produtoPage.abrirFecharMenu();
    }

    @Test
    public void t02_deveAbrirItensDoMenuAllItems() {
        loginPage.loginComoStandardUser();
        produtoPage.itensDoMenuAllItems();
    }

    @Test
    public void t03_deveAbrirItensDoMenuAbout() {
        loginPage.loginComoStandardUser();
        produtoPage.itensDoMenuAbout();

        Assertions.assertTrue(getDriver().getCurrentUrl().contains("saucelabs"));
    }

    @Test
    public void t04_deveAbrirItensDoMenuLogout() {
        loginPage.loginComoStandardUser();
        produtoPage.itensDoMenuLogout();
    }

    @Test
    public void t05_deveAbrirItensDoMenuResetAppState() {
        loginPage.loginComoStandardUser();
        produtoPage.itensDoMenuResetAppState();
    }


}

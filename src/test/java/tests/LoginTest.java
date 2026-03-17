package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;

import static core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Test
    public void naoDeveLogarComCredenciaisInvalidas() {
        LoginPage loginPage = new LoginPage();

        var usuarioInexistente = "TESTE";
        var senhaInexistente = "TESTE123";
        var mensagemEsperada = "Epic sadface: Username and password do not match any user in this service";

        loginPage.tentarLogin(usuarioInexistente, senhaInexistente);

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void naoDeveLogarComCredenciaisVazias() {
        LoginPage loginPage = new LoginPage();

        var mensagemEsperada = "Epic sadface: Username is required";

        loginPage.tentarLogin("", "");

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void naoDeveLogarSemUsuario() {
        LoginPage loginPage = new LoginPage();
        var mensagemEsperada = "Epic sadface: Username is required";

        loginPage.tentarLogin("", "secret_sauce");

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void naoDeveLogarSemSenha() {
        LoginPage loginPage = new LoginPage();

        var mensagemEsperada = "Epic sadface: Password is required";

        loginPage.tentarLogin("standard_user", "");

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void deveFazerLoginComCredenciaisValidasStandardUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.tentarLogin("standard_user", "secret_sauce");

        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("https://www.saucedemo.com/inventory.html"));
    }
    @Test
    public void deveFazerLoginComCredenciaisValidasLockedOutUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.tentarLogin("locked_out_user", "secret_sauce");

        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void deveFazerLoginComCredenciaisValidasProblemUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.tentarLogin("problem_user", "secret_sauce");

        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void deveFazerLoginComCredenciaisValidasPerformanceGlitchUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.tentarLogin("performance_glitch_user", "secret_sauce");

        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void deveFazerLoginComCredenciaisValidasErrorUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.tentarLogin("error_user", "secret_sauce");

        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void deveFazerLoginComCredenciaisValidasVisualUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.tentarLogin("visual_user", "secret_sauce");

        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("https://www.saucedemo.com/inventory.html"));
    }


}

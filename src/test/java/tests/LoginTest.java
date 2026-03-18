package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;
import utils.Usuarios;

import static core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Test
    public void t01_naoDeveLogarComCredenciaisInvalidas() {
        var usuarioInexistente = "TESTE";
        var senhaInexistente = "TESTE123";
        var mensagemEsperada = "Epic sadface: Username and password do not match any user in this service";

        loginPage.tentarLogin(usuarioInexistente, senhaInexistente);

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void t02_naoDeveLogarComCredenciaisVazias() {
        var mensagemEsperada = "Epic sadface: Username is required";

        loginPage.tentarLogin("", "");

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void t03_naoDeveLogarSemUsuario() {
        var mensagemEsperada = "Epic sadface: Username is required";

        loginPage.tentarLogin("", "secret_sauce");

        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());

        loginPage.fecharMensagemErro();
    }

    @Test
    public void t04_naoDeveLogarSemSenha() {
        var mensagemEsperada = "Epic sadface: Password is required";

        loginPage.tentarLogin("standard_user", "");
        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());
        loginPage.fecharMensagemErro();
    }

    @Test
    public void t05_deveFazerLoginComCredenciaisValidasStandardUser() {
        loginPage.loginComoStandardUser();
        Assertions.assertTrue(loginPage.estaNaPaginaDeProdutos());
    }

    @Test
    public void t06_naoDeveLogarComLockedOutUser() {
        loginPage.naoDeveLogarComoLockedOutUser();
        var mensagemEsperada = "Epic sadface: Sorry, this user has been locked out.";
        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());
        loginPage.fecharMensagemErro();
    }

    @Test
    public void t07_deveFazerLoginComCredenciaisValidasProblemUser() {
        loginPage.loginComoProblemUser();
        Assertions.assertTrue(loginPage.estaNaPaginaDeProdutos());
    }

    @Test
    public void t08_deveFazerLoginComCredenciaisValidasPerformanceGlitchUser() {
        loginPage.loginComoPerformanceGlitchUser();
        Assertions.assertTrue(loginPage.estaNaPaginaDeProdutos());
    }

    @Test
    public void t09_deveFazerLoginComCredenciaisValidasErrorUser() {
        loginPage.loginComoErrorUser();
        Assertions.assertTrue(loginPage.estaNaPaginaDeProdutos());

    }

    @Test
    public void t10_deveFazerLoginComCredenciaisValidasVisualUser() {
        loginPage.loginComoVisualUser();
        Assertions.assertTrue(loginPage.estaNaPaginaDeProdutos());
    }
}

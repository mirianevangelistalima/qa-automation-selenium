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
        loginComoStandardUser();
    }

    @Test
    public void t06_naoDeveLogarComLockedOutUser() {
        naoDeveLogarComoLockedOutUser();
    }

    @Test
    public void t07_deveFazerLoginComCredenciaisValidasProblemUser() {
        loginComoProblemUser();
    }

    @Test
    public void t08_deveFazerLoginComCredenciaisValidasPerformanceGlitchUser() {
        loginComoPerformanceGlitchUser();
    }

    @Test
    public void t09_deveFazerLoginComCredenciaisValidasErrorUser() {
        loginComoErrorUser();
    }

    @Test
    public void t10_deveFazerLoginComCredenciaisValidasVisualUser() {
        loginComoVisualUser();
    }

    public void loginComoStandardUser() {
        loginPage.tentarLogin(Usuarios.STANDARD_USER, Usuarios.PASSWORD);
        validarUrl();
    }

    public void naoDeveLogarComoLockedOutUser() {
        loginPage.tentarLogin(Usuarios.LOCKED_OUT_USER, Usuarios.PASSWORD);

        var mensagemEsperada = "Epic sadface: Sorry, this user has been locked out.";
        Assertions.assertEquals(mensagemEsperada, loginPage.getMensagemErro());
        loginPage.fecharMensagemErro();
    }

    public void loginComoProblemUser() {
        loginPage.tentarLogin(Usuarios.PROBLEM_USER, Usuarios.PASSWORD);
        validarUrl();
    }
    public void loginComoPerformanceGlitchUser() {
        loginPage.tentarLogin(Usuarios.PERFORMANCE_GLITCH_USER, Usuarios.PASSWORD);
        validarUrl();
    }
    public void loginComoErrorUser() {
        loginPage.tentarLogin(Usuarios.ERROR_USER, Usuarios.PASSWORD);
        validarUrl();
    }
    public void loginComoVisualUser() {
        loginPage.tentarLogin(Usuarios.VISUAL_USER, Usuarios.PASSWORD);
        validarUrl();
    }
    public void validarUrl(){
        String urlAtual = getDriver().getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("inventory.html"));
    }

}

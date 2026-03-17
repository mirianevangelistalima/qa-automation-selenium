package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;

import static core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Test
    @Order(1)

    public void deveDigitarNomeUsuarioInexistente() {
        var usuarioInexistente = "TESTE";
        var senhaInexistente = "TESTE123";
        var mensagemErroUsuarioSenhaIncorreta = "Epic sadface: Username and password do not match any user in this service";

        loginPage.cliqueUserName();
        loginPage.preencherUserName(usuarioInexistente);
        loginPage.cliquePassword();
        loginPage.preencherPassword(senhaInexistente);
        loginPage.cliqueButtonLogin();
        Assertions.assertEquals(mensagemErroUsuarioSenhaIncorreta, loginPage.getMensagemErro());
    }

    @Test
    @Order(2)
    public void deveDeixarCredenciaisVazia() {
        var mensagemErroUsuarioSenhaObrigatorio = "Epic sadface: Username is required";

        loginPage.cliqueUserName();
        loginPage.preencherUserName("");
        loginPage.cliquePassword();
        loginPage.preencherPassword("");
        loginPage.cliqueButtonLogin();
        Assertions.assertEquals(mensagemErroUsuarioSenhaObrigatorio, loginPage.getMensagemErro());
     //   deveFecharModalErro();
    }

//    @Test
//    @Order(3)
    private void deveFecharModalErro() {
        loginPage.cliqueButtonErro();
    }
//    public void deveFazerLoginComCredenciaisValidas() {
//        loginPage.cliqueUserName();
//    }
}

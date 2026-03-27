package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;

import static core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class CheckoutTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    ProductPage produtoPage = new ProductPage();
    CartPage carrinhoPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    private void fluxoAteOCheckout(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarProdutoAoCarrinho("sauce-labs-bike-light");
        carrinhoPage.clicarNoBotaoCart();
        carrinhoPage.clicarNoBotaoCheckout();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"));
    }

    @ParameterizedTest(name = "t01_Fluxo checkout com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t01_deveFazerFluxoCheckoutValido(String usuario, String senha) {
        fluxoAteOCheckout(usuario, senha);
    }

    @ParameterizedTest(name = "t02_Preencher informações do checkout com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t02_devePreencherInformacoesCheckout(String usuario, String senha) {
        fluxoAteOCheckout(usuario, senha);
        checkoutPage.preencherFirstName("Mirian");
        checkoutPage.preencherLastName("L");
        checkoutPage.preencherPostalCode("12345");
        checkoutPage.clicarEmContinuar();
    }

    @ParameterizedTest(name = "t03_Checkout vazio com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t03_deveValidarCheckoutVazio(String usuario, String senha) {
        fluxoAteOCheckout(usuario, senha);
        checkoutPage.clicarEmContinuar();
        Assertions.assertEquals("Error: First Name is required", checkoutPage.getMensagemErro());
    }

    @ParameterizedTest(name = "t04_LastName vazio com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosQueValidamCorretamente")
    void t04_deveValidarCampoLastNameVazio(String usuario, String senha) {
        fluxoAteOCheckout(usuario, senha);
        checkoutPage.preencherFirstName("Mirian");
        checkoutPage.preencherLastName("");
        checkoutPage.preencherPostalCode("12345");
        checkoutPage.clicarEmContinuar();
        Assertions.assertEquals("Error: Last Name is required", checkoutPage.getMensagemErro());
    }

    @ParameterizedTest(name = "t05_LastName bugado com {0}")
    @MethodSource("data.UsuariosData#usuariosProblemUser")
    void t05_deveValidarBugCampoLastNameProblemUser(String usuario, String senha) {
        fluxoAteOCheckout(usuario, senha);

        // Digita First Name
        checkoutPage.preencherFirstName("Mirian");

        // Tenta digitar Last Name
        checkoutPage.preencherLastName("X");

        // Verifica que o Last Name continua vazio
        Assertions.assertEquals("", checkoutPage.getValorCampoLastName(),
                "error_user não deveria conseguir digitar no campo Last Name");

        // E que o First Name foi sobrescrito pelo último caractere
        Assertions.assertEquals("X", checkoutPage.getValorCampoFirstName(),
                "error_user sobrescreve o First Name com o último caractere do Last Name");
    }


    @ParameterizedTest(name = "t06_PostalCode inválido com usuário {0}")
    @MethodSource("data.UsuariosData#todosUsuarios")
    void t06_deveValidarCampoCEPComLetra(String usuario, String senha) {
        fluxoAteOCheckout(usuario, senha);
        checkoutPage.preencherFirstName("Mirian");
        checkoutPage.preencherLastName("L");
        checkoutPage.preencherPostalCode("Code");
        checkoutPage.clicarEmContinuar();
    }
}

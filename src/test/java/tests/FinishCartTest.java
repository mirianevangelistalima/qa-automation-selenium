package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class FinishCartTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    ProductPage produtoPage = new ProductPage();
    CartPage carrinhoPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    FinishCartPage finishCartPage = new FinishCartPage();

    private void fluxoAteFinish(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));

        produtoPage.adicionarProdutoAoCarrinho("sauce-labs-bike-light");
        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"));

        carrinhoPage.clicarNoBotaoCheckout();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"));

        checkoutPage.preencherTodasInformacoes("Mirian", "L", "12345");
        checkoutPage.clicarEmContinuar();
    }

    @ParameterizedTest(name = "t01_Fluxo até finish com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t01_deveFazerFluxoAteFinish(String usuario, String senha) {
        fluxoAteFinish(usuario, senha);
    }

    @ParameterizedTest(name = "t02_Validar preço no carrinho com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t02_deveValidarPrecoDoProdutoNoCarrinho(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);

        String nomeProduto = "Sauce Labs Backpack";
        String productId = "sauce-labs-backpack";

        String precoNaLista = produtoPage.pegarPrecoDoProduto(nomeProduto);
        produtoPage.adicionarProdutoAoCarrinho(productId);
        carrinhoPage.clicarNoBotaoCart();

        String precoNoCarrinho = carrinhoPage.pegarPrecoNoCarrinho(nomeProduto);

        if (usuario.equals("visual_user")) {
            // visual_user mostra preço incorreto
            Assertions.assertNotEquals(precoNaLista, precoNoCarrinho,
                    "visual_user deveria mostrar preço diferente");
        } else {
            assertEquals(precoNaLista, precoNoCarrinho,
                    "Preço incorreto para usuário: " + usuario);
        }
    }

    @ParameterizedTest(name = "t03_Finalizar compra com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t03_deveFinalizarCompraUsuariosValidos(String usuario, String senha) {
        fluxoAteFinish(usuario, senha);
        finishCartPage.clicarFinish();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/checkout-complete.html"),
                "Fluxo não finalizou corretamente para usuário: " + usuario);

        finishCartPage.voltarParaPaginaDeProdutos();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));
    }

    @ParameterizedTest(name = "t04_Falha ao finalizar compra com problem_user {0}")
    @MethodSource("data.UsuariosData#usuariosProblemUser")
    void t04_deveFalharFinalizarCompraProblemUser(String usuario, String senha) {
        fluxoAteFinish(usuario, senha);

        // problem_user não deve conseguir clicar Finish ou concluir
        Assertions.assertThrows(Exception.class, () -> finishCartPage.clicarFinish(),
                "problem_user não deveria conseguir finalizar compra");
    }

    @ParameterizedTest(name = "t05_Falha ao finalizar compra com error_user {0}")
    @MethodSource("data.UsuariosData#usuariosError")
    void t05_deveFalharFinalizarCompraErrorUser(String usuario, String senha) {
        fluxoAteFinish(usuario, senha);
        finishCartPage.clicarFinish();

        Assertions.assertFalse(getDriver().getCurrentUrl().contains("/checkout-complete.html"),
                "error_user não deveria finalizar compra");
    }
}

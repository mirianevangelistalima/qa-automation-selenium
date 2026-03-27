package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class CartTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    ProductPage produtoPage = new ProductPage();
    CartPage carrinhoPage = new CartPage();

    @ParameterizedTest(name = "t01_Clicar no carrinho com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t01_deveClicarNoBotaoCart(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"));
    }

    @ParameterizedTest(name = "t02_Verificar itens no carrinho com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t02_deveVerificarItensNoCarrinho(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");
        assertEquals("2", produtoPage.getQuantidadeCarrinho());

        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"));
    }

    @ParameterizedTest(name = "t03_Remover produto com usuário válido {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t03_deveRemoverProdutoDoCarrinhoValido(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");

        carrinhoPage.clicarNoBotaoCart();
        carrinhoPage.removerProdutoDoCarrinho("sauce-labs-bike-light");
        assertEquals("1", produtoPage.getQuantidadeCarrinho());
    }

    @ParameterizedTest(name = "t04_Remover produto falha com problem_user {0}")
    @MethodSource("data.UsuariosData#usuariosProblemUser")
    void t04_deveFalharRemoverProdutoDoCarrinhoProblem(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");

        carrinhoPage.clicarNoBotaoCart();
        carrinhoPage.removerProdutoDoCarrinho("sauce-labs-bike-light");
        Assertions.assertNotEquals("1", produtoPage.getQuantidadeCarrinho(),
                "problem_user não deveria conseguir remover produto");
    }

    @ParameterizedTest(name = "t05_Remove carrinho com error_user {0}")
    @MethodSource("data.UsuariosData#usuariosError")
    void t05_deveRemoverProdutoDoCarrinhoError(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");

        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(carrinhoPage.isBotaoRemovePresente("sauce-labs-bike-light"),
                "error_user deveria exibir botão Remove");
    }

    @ParameterizedTest(name = "t06_Voltar para produtos com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t06_deveVoltarParaProdutos(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");

        carrinhoPage.clicarNoBotaoCart();
        carrinhoPage.clicarNoBotaoVoltarParaShopping();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));
    }

    @ParameterizedTest(name = "t07_Ir para checkout com usuário {0}")
    @MethodSource("data.UsuariosData#usuariosValidos")
    void t07_deveIrParaCheckout(String usuario, String senha) {
        loginPage.tentarLogin(usuario, senha);
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");

        carrinhoPage.clicarNoBotaoCart();
        carrinhoPage.clicarNoBotaoCheckout();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"));
    }
}

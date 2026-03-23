package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

    private void abrirCarrinho() {
        loginPage.loginComoStandardUser();
        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"));
    }

    private void adicionarProdutosAoCarrinho() {
        loginPage.loginComoStandardUser();
        produtoPage.adicionarMultiplosProdutosAoCarrinho("sauce-labs-bike-light", "sauce-labs-bolt-t-shirt");
        assertEquals("2", produtoPage.getQuantidadeCarrinho());
    }

    @Test
    public void t01_deveClicarNoBotaoCart() {
        abrirCarrinho();
    }

    @Test
    public void t02_deveVerificarItensNoCarrinho() {
        adicionarProdutosAoCarrinho();

        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"));
    }

    @Test
    public void t03_deveRemoverProdutoDoCarrinho() {
        adicionarProdutosAoCarrinho();

        carrinhoPage.clicarNoBotaoCart();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/cart.html"));

        carrinhoPage.removerProdutoDoCarrinho("sauce-labs-bike-light");
        assertEquals("1", produtoPage.getQuantidadeCarrinho());
    }

    @Test
    public void t04_deveVoltarParaProdutos() {
        adicionarProdutosAoCarrinho();
        carrinhoPage.clicarNoBotaoCart();

        carrinhoPage.clicarNoBotaoVoltarParaShopping();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));

    }

    @Test
    public void t05_deveIrParaCheckout() {
        adicionarProdutosAoCarrinho();
        carrinhoPage.clicarNoBotaoCart();

        carrinhoPage.clicarNoBotaoCheckout();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"));

    }

}

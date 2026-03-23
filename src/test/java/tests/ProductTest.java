package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.LoginPage;
import pages.ProductPage;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    ProductPage produtoPage = new ProductPage();

    @Test
    public void t01_deveAbrirMenu() {
        loginPage.loginComoStandardUser();
        produtoPage.abrirMenu();
        produtoPage.fecharMenu();
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

    @Test
    public void t06_deveOrganizarPorZtoA() {
        loginPage.loginComoStandardUser();
        produtoPage.ordenarSelecionarZtoA();
    }

    @Test
    public void t07_deveOrganizarPriceBaixoAoAlto() {
        loginPage.loginComoStandardUser();
        produtoPage.ordenarPorPriceBaixoAoAlto();
    }

    @Test
    public void t08_deveOrganizarPorPriceAltoAoBaixo() {
        loginPage.loginComoStandardUser();
        produtoPage.ordenarPorPriceAltoAoBaixo();
    }

    @Test
    public void t09_deveAdicionarProdutoAoCarrinho() {
        loginPage.loginComoStandardUser();
        produtoPage.adicionarProdutoAoCarrinho("sauce-labs-backpack");

        assertEquals("1", produtoPage.getQuantidadeCarrinho());
    }

    @Test
    public void t10_deveRemoverProdutoDoCarrinho() {
        loginPage.loginComoStandardUser();
        produtoPage.adicionarProdutoAoCarrinho("sauce-labs-backpack");
        produtoPage.removerProdutoDoCarrinho("sauce-labs-backpack");

        assertEquals("", produtoPage.getQuantidadeCarrinho());
    }

    @Test
    public void t11_deveAdicionarMultiplosProdutosAoCarrinho() {
        loginPage.loginComoStandardUser();
        produtoPage.adicionarMultiplosProdutosAoCarrinho(
                "sauce-labs-bike-light",
                "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket",
                "sauce-labs-onesie",
                "test.allthethings()-t-shirt-(red)"
        );

        assertEquals("5", produtoPage.getQuantidadeCarrinho());
    }
}
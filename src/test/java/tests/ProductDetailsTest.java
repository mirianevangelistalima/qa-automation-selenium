package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductPage;

import static core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class ProductDetailsTest extends  BaseTest {

    LoginPage loginPage = new LoginPage();
    ProductPage produtoPage = new ProductPage();
    ProductDetailsPage produtoDetalhesPage = new ProductDetailsPage();

    String  nomeProduto = "Test.allTheThings() T-Shirt (Red)";

    private void abrirDetalheDoProduto() {
        loginPage.loginComoStandardUser();
        produtoDetalhesPage.clicarNoTituloEAbrirMaisDetalhe(nomeProduto);
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory-item.html?id=3"));
    }

    @Test
    public void t01_deveAbrirDetalhesDoProduto() {
        abrirDetalheDoProduto();
    }

    @Test
    public void t02_deveAdicionarProdutoAoCarrinho() {
        abrirDetalheDoProduto();

        produtoDetalhesPage.adicionarProdutoAoCarrinho();
        Assertions.assertTrue(produtoDetalhesPage.isBotaoRemoveVisivel());
        assertEquals("1", produtoPage.getQuantidadeCarrinho());
    }

    @Test
    public void t03_deveRemoverProdutoDoCarrinho() {
        abrirDetalheDoProduto();

        produtoDetalhesPage.adicionarProdutoAoCarrinho();
        produtoDetalhesPage.removerProdutoDoCarrinho();
        assertEquals("", produtoPage.getQuantidadeCarrinho());
    }

    @Test
    public void t04_deveClicarNoBotaoBackToProducts() {
        abrirDetalheDoProduto();

        produtoDetalhesPage.clicarNoBotaoBackToProducts();
        Assertions.assertTrue(getDriver().getCurrentUrl().contains("/inventory.html"));
    }

}

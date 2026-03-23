package pages;

import core.BasePage;
import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {
    private final By botaoBackToProducts = By.id("back-to-products");
    private final By botaoAddToCart = By.id("add-to-cart");
    private final By botaoRemoveProdutoCart = By.id("remove");

    public void adicionarProdutoAoCarrinho(){
        findElement(botaoAddToCart).click();
    }
    public void clicarNoBotaoBackToProducts() {
        findElement(botaoBackToProducts).click();
    }
    public void removerProdutoDoCarrinho() {
        findElement(botaoRemoveProdutoCart).click();
    }

    public boolean isBotaoRemoveVisivel() {
        return findElement(botaoRemoveProdutoCart).isDisplayed();
    }
    public void clicarNoTituloEAbrirMaisDetalhe(String nomeProduto) {
        findElement(linkTituloProduto(nomeProduto)).click();
    }

    private By linkTituloProduto(String nomeProduto) {
        return By.xpath("//a[.//div[text()='" + nomeProduto + "']]");
    }
}

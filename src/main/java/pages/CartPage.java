package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CartPage extends BasePage {

    private final By botaoCart = By.id("shopping_cart_container");
    private final By botaoVoltarParaShopping = By.id("continue-shopping");
    private final By botaoCheckout = By.id("checkout");
    private final By itensCarrinho = By.className("cart_item");

    public void clicarNoBotaoCart() {
        findElement(botaoCart).click();
    }

    public void removerProdutoDoCarrinho(String productId) {
        try {
            findElement(botaoRemoverProduto(productId)).click();
        } catch (NoSuchElementException e) {
            System.out.println("Botão de remover não encontrado para produto: " + productId);
        }
    }

    public void clicarNoBotaoVoltarParaShopping() {
        findElement(botaoVoltarParaShopping).click();
    }

    private By botaoRemoverProduto(String productId) {
        return By.id("remove-" + productId);
    }

    public void clicarNoBotaoCheckout() {
        findElement(botaoCheckout).click();
    }

    public String pegarPrecoNoCarrinho(String nomeProduto) {
        By preco = By.xpath("//div[text()='" + nomeProduto + "']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']");
        return findElement(preco).getText();
    }

    public boolean isBotaoRemovePresente(String productId) {
        try {
            return findElement(botaoRemoverProduto(productId)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

package pages;

import core.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By botaoCart = By.id("shopping_cart_container");
    private final By botaoVoltarParaShopping = By.id("continue-shopping");
    private final By botaoCheckout = By.id("checkout");

    public void clicarNoBotaoCart() {
        findElement(botaoCart).click();
    }

    public void removerProdutoDoCarrinho(String productId) {
        findElement(botaoRemoverProduto(productId)).click();
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
}

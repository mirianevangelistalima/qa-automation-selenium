package pages;

import core.BasePage;
import org.openqa.selenium.By;


public class FinishCartPage extends BasePage {
    private final By botaoFinish = By.id("finish");
    private final By botaoBackHome = By.id("back-to-products");

    public void clicarFinish() {
        findElement(botaoFinish).click();
    }

    public void voltarParaPaginaDeProdutos() {
        findElement(botaoBackHome).click();
    }
}

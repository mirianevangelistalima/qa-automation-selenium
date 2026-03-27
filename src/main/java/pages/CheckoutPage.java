package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CheckoutPage extends BasePage {
    private final By inputFirstName = By.id("first-name");
    private final By inputLastName = By.id("last-name");
    private final By inputPostalCode = By.id("postal-code");
    private final By inputBotaoContinue = By.id("continue");
    private final By mensagemErro = By.xpath("//h3[@data-test='error']");

    public void preencherTodasInformacoes(String firstName, String lastName, String postalCode) {
        preencherFirstName(firstName);
        preencherLastName(lastName);
        preencherPostalCode(postalCode);
    }

    public void preencherFirstName(String name) {
        findElement(inputFirstName).clear();
        findElement(inputFirstName).sendKeys(name);
    }

    public void preencherLastName(String lastName) {
        findElement(inputLastName).clear();
        findElement(inputLastName).sendKeys(lastName);
    }

    public void preencherPostalCode(String postalCode) {
        findElement(inputPostalCode).clear();
        findElement(inputPostalCode).sendKeys(postalCode);
    }

    public void clicarEmContinuar() {
        findElement(inputBotaoContinue).click();
    }

    public String getMensagemErro() {
        return findElement(mensagemErro).getText();
    }

    public String getValorCampoFirstName() {
        return findElement(inputFirstName).getAttribute("value");
    }

    public String getValorCampoLastName() {
        return findElement(inputLastName).getAttribute("value");
    }

}

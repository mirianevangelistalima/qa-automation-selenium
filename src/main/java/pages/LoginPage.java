package pages;

import core.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By inputUserName = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By inptButtonLogin = By.id("login-button");
    private final By mensagemErro = By.xpath("//h3[@data-test='error']");
    private final By botaoFecharErro = By.cssSelector("[data-test='error-button']");

    public void preencherUserName(String userName) {
        findElement(inputUserName).click();
        findElement(inputUserName).clear();
        findElement(inputUserName).sendKeys(userName);
    }

    public void preencherPassword(String password) {
        findElement(inputPassword).click();
        findElement(inputPassword).clear();
        findElement(inputPassword).sendKeys(password);
    }

    public void tentarLogin(String userName, String password) {
        preencherUserName(userName);
        preencherPassword(password);
        clicarLogin();
    }
    public void clicarLogin() {
        findElement(inptButtonLogin).click();
    }

    public String getMensagemErro() {
        return findElement(mensagemErro).getText();
    }

    public void cliqueButtonErro() {
        findElement(botaoFecharErro).click();
    }
    public void fecharMensagemErro() {
        findElement(botaoFecharErro).click();
    }
}


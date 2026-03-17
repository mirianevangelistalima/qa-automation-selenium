package pages;

import core.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By inptUserName = By.id("user-name");
    private final By inptPassword = By.id("password");
    private final By inptButtonLogin = By.id("login-button");
    private final By mensagemErro = By.xpath("//h3[@data-test='error']");
    private final By buttonErro = By.xpath("//button[@data-test='error']");


    public void cliqueUserName() {
        findElement(inptUserName).click();
    }

    public void preencherUserName(String userName) {
        findElement(inptUserName).sendKeys(userName);
    }

    public void cliquePassword() {
        findElement(inptPassword).click();
    }

    public void preencherPassword(String password) {
        findElement(inptPassword).sendKeys(password);
    }

    public void cliqueButtonLogin() {
        findElement(inptButtonLogin).click();
    }

    public String getMensagemErro() {
        return findElement(mensagemErro).getText();
    }

    public void cliqueButtonErro() {
        findElement(buttonErro).click();
    }
}


package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage {
    private final By inputUserName = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By inptButtonLogin = By.id("login-button");
    private final By mensagemErro = By.xpath("//h3[@data-test='error']");
    private final By botaoFecharErro = By.cssSelector("[data-test='error-button']");
    private final By botaoAddToCart = By.id("add-to-cart-sauce-labs-backpack");

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

    public void fecharMensagemErro() {
        findElement(botaoFecharErro).click();
    }

    public void shortWait(){
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated(botaoAddToCart));
    }
}


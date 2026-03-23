package pages;

import core.BasePage;
import org.openqa.selenium.By;
import data.UserData;


import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage {
    private final By inputUserName = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By inputButtonLogin = By.id("login-button");
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
        findElement(inputButtonLogin).click();
    }
    public String getMensagemErro() {
        return findElement(mensagemErro).getText();
    }

    public void fecharMensagemErro() {
        findElement(botaoFecharErro).click();
    }

    public void loginComoStandardUser() {
        tentarLogin(UserData.STANDARD_USER, UserData.PASSWORD);
    }

    public void naoDeveLogarComoLockedOutUser() {
        tentarLogin(UserData.LOCKED_OUT_USER, UserData.PASSWORD);
    }

    public void loginComoProblemUser() {
        tentarLogin(UserData.PROBLEM_USER, UserData.PASSWORD);
    }
    public void loginComoPerformanceGlitchUser() {
        tentarLogin(UserData.PERFORMANCE_GLITCH_USER, UserData.PASSWORD);
    }
    public void loginComoErrorUser() {
        tentarLogin(UserData.ERROR_USER, UserData.PASSWORD);
    }
    public void loginComoVisualUser() {
        tentarLogin(UserData.VISUAL_USER, UserData.PASSWORD);
    }
    public boolean estaNaPaginaDeProdutos(){
        return getDriver().getCurrentUrl().contains("inventory.html");
    }

}


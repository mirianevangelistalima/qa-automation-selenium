package pages;

import core.BasePage;
import org.openqa.selenium.By;
import utils.Usuarios;


import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage {
    private final By inputUserName = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By inputButtonLogin = By.id("login-button");
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
        findElement(inputButtonLogin).click();
    }
    public String getMensagemErro() {
        return findElement(mensagemErro).getText();
    }

    public void fecharMensagemErro() {
        findElement(botaoFecharErro).click();
    }

    public void loginComoStandardUser() {
        tentarLogin(Usuarios.STANDARD_USER, Usuarios.PASSWORD);
    }

    public void naoDeveLogarComoLockedOutUser() {
        tentarLogin(Usuarios.LOCKED_OUT_USER, Usuarios.PASSWORD);
    }

    public void loginComoProblemUser() {
        tentarLogin(Usuarios.PROBLEM_USER, Usuarios.PASSWORD);
    }
    public void loginComoPerformanceGlitchUser() {
        tentarLogin(Usuarios.PERFORMANCE_GLITCH_USER, Usuarios.PASSWORD);
    }
    public void loginComoErrorUser() {
        tentarLogin(Usuarios.ERROR_USER, Usuarios.PASSWORD);
    }
    public void loginComoVisualUser() {
        tentarLogin(Usuarios.VISUAL_USER, Usuarios.PASSWORD);
    }
    public boolean estaNaPaginaDeProdutos(){
        return getDriver().getCurrentUrl().contains("inventory.html");
    }

}


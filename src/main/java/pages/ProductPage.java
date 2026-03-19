package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.DriverFactory.getDriver;

public class ProductPage extends BasePage {
    //Menu
    private final By botaoMenu = By.id("react-burger-menu-btn");
    private final By botaoFecharMenu = By.id("react-burger-cross-btn");
    private final By botaoAllItems = By.xpath("//a[contains(.,'All Items')]");
    private final By botaoAbout = By.xpath("//a[contains(.,'About')]");
    private final By botaoLogout = By.xpath("//a[contains(.,'Logout')]");
    private final By botaoResetAppState = By.xpath("//a[contains(.,'Reset App State')]");

    private final By botaoAddToCart = By.id("add-to-cart-sauce-labs-backpack");

    public void abrirFecharMenu() {
        findElement(botaoMenu).click();
        findElement(botaoFecharMenu).click();
        shortWait(botaoFecharMenu);
    }

    public void itensDoMenuAllItems() {
        abrirFecharMenu();
        findElement(botaoAllItems).click();
    }

    public void itensDoMenuAbout() {
        abrirFecharMenu();
        findElement(botaoAbout).click();
    }

    public void itensDoMenuLogout() {
        abrirFecharMenu();
        findElement(botaoLogout).click();
    }

    public void itensDoMenuResetAppState() {
        abrirFecharMenu();
        findElement(botaoResetAppState).click();
    }

    private void shortWait(By EsperaPor) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(EsperaPor));

    }
}

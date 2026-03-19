package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static core.DriverFactory.getDriver;

public class BasePage {
    private final WebDriverWait wait;

    public BasePage(){
        // espera o elemento aparecer na tela, timeout de 15s
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constantes.TIMEOUT));
    }

    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }

}

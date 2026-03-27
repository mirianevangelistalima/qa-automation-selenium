package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static core.DriverFactory.getDriver;

public class ProductPage extends BasePage {

    // Menu
    private final By botaoMenu = By.id("react-burger-menu-btn");
    private final By botaoFecharMenu = By.id("react-burger-cross-btn");
    private final By botaoAllItems = By.xpath("//a[contains(.,'All Items')]");
    private final By botaoAbout = By.xpath("//a[contains(.,'About')]");
    private final By botaoLogout = By.xpath("//a[contains(.,'Logout')]");
    private final By botaoResetAppState = By.xpath("//a[contains(.,'Reset App State')]");
    // Ordenação
    private final By abrirSelecaoOrdenacao = By.className("product_sort_container");
    // Cart
    private final By badgeCarrinho = By.className("shopping_cart_badge");

    public void abrirMenu() {
        findElement(botaoMenu).click();
        shortWait(botaoFecharMenu);
    }

    public void fecharMenu() {
        findElement(botaoFecharMenu).click();
    }

    public void itensDoMenuAllItems() {
        abrirMenu(); //chamar no test
        findElement(botaoAllItems).click();
    }

    public void itensDoMenuAbout() {
        abrirMenu();
        findElement(botaoAbout).click();
    }

    public void itensDoMenuLogout() {
        abrirMenu();
        findElement(botaoLogout).click();
    }

    public void itensDoMenuResetAppState() {
        abrirMenu();
        findElement(botaoResetAppState).click();
    }

    public void ordenarSelecionarAtoZ() {
        findElement(abrirSelecaoOrdenacao).click();
        findElement(By.xpath("//option[contains(.,'Name (A to Z)')]")).click();
    }

    public void ordenarSelecionarZtoA() {
        findElement(abrirSelecaoOrdenacao).click();
        findElement(By.xpath("//option[contains(.,'Name (Z to A)')]")).click();
    }

    public void ordenarPorPriceBaixoAoAlto() {
        findElement(abrirSelecaoOrdenacao).click();
        findElement(By.xpath("//option[contains(.,'Price (low to high)')]")).click();
    }

    public void ordenarPorPriceAltoAoBaixo() {
        findElement(abrirSelecaoOrdenacao).click();
        findElement(By.xpath("//option[contains(.,'Price (high to low)')]")).click();
    }

    public void adicionarProdutoAoCarrinho(String productId) {
        findElement(botaoAdicionarProduto(productId)).click();
    }

    public void removerProdutoDoCarrinho(String productId) {
        findElement(botaoRemoverProduto(productId)).click();
    }


    public void adicionarMultiplosProdutosAoCarrinho(String... productIds) {
        for (String productId : productIds) {
            findElement(botaoAdicionarProduto(productId)).click();
        }
    }

    public String getQuantidadeCarrinho() {
        if (findElements(badgeCarrinho).isEmpty()) {
            return "";
        }
        return findElements(badgeCarrinho).get(0).getText();
    }
    public String pegarPrecoDoProduto(String nomeProduto) {
        By preco = By.xpath("//div[text()='" + nomeProduto + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
        return findElement(preco).getText();
    }

    private By botaoAdicionarProduto(String productId) {
        return By.id("add-to-cart-" + productId);
    }

    private By botaoRemoverProduto(String productId) {
        return By.id("remove-" + productId);
    }

    private void shortWait(By esperaPor) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(esperaPor));
    }

    public boolean isOrdenacaoAplicada() {
        List<WebElement> elementosAntes = getDriver().findElements(By.className("inventory_item_name"));
        List<String> nomesAntes = elementosAntes.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // aplica a ordenação
        ordenarSelecionarAtoZ();

        List<WebElement> elementosDepois = getDriver().findElements(By.className("inventory_item_name"));
        List<String> nomesDepois = elementosDepois.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // se a lista mudou, a ordenação foi aplicada
        return !nomesAntes.equals(nomesDepois);
    }






}
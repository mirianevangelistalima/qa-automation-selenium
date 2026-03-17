package tests;

import core.Constantes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

import static core.DriverFactory.*;

public class BaseTest {

    @BeforeEach
    public void setup(){
        getDriver().get(Constantes.SAUCE_DEMO_URL);
    }

    @AfterEach
    public void tearDown() throws IOException {
        File source = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File("ultimoTesteExecutado.png"));

        killDriver();
    }
}

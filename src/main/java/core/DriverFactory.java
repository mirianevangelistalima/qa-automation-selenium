package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;
    private DriverFactory(){}

    public static WebDriver getDriver(){
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    /*
    * Fecha o driver
    */
public static void killDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
}


}

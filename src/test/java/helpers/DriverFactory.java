package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;

/**
 * Web driver factory.
 */
public final class DriverFactory {

    /**
     * Не вызывается.
     */
    private DriverFactory() {
    }

    /**
     * Получение драйвера.
     *
     * @return WebDriver
     */
    private static WebDriver getDriver() throws IOException {
        String browserName = ParametersProvider.getProperty("browser");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    ParametersProvider.getProperty("driverPath"));
            return new ChromeDriver();
        } else {
            throw new IllegalStateException("Chosen browser not supported");
        }
    }

    /**
     * Создание web driver c заданными конфигурациями.
     *
     * @return web driver
     * @throws IOException когда файл с параметрами не доступен.
     */
    public static WebDriver createDriver()
            throws IOException {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(ParametersProvider.getProperty("timeout"))));
        return driver;
    }
}

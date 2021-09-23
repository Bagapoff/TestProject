package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Waiter {

    /**
     * Основание системы счисления для приведения к long.
     */
    private static final int RADIX = 10;

    /**
     * Не вызывается.
     */
    private Waiter() {
    }


    /**
     * Ожидание невидимости элемента.
     *
     * @param webDriver  web driver
     * @param webElement элемент на странице
     * @throws IOException когда файл параметров недоступен
     */
    public static void waitUntilInvisible(
            final WebDriver webDriver,
            final WebElement webElement)
            throws IOException {
        long explicitTimeout = Long.parseLong(ParametersProvider
                .getProperty("wait"), RADIX);
        new WebDriverWait(webDriver, explicitTimeout).until(ExpectedConditions
                .invisibilityOf(webElement));
    }

    /**
     * Ожидание видимости элемента.
     *
     * @param webDriver  web driver
     * @param webElement элемент на странице
     * @throws IOException когда файл параметров недоступен
     */
    public static void waitUntilVisible(
            final WebDriver webDriver,
            final WebElement webElement)
            throws IOException {
        long explicitTimeout = Long.parseLong(ParametersProvider
                .getProperty("wait"), RADIX);
        new WebDriverWait(webDriver, explicitTimeout).until(ExpectedConditions
                .visibilityOf(webElement));
    }

}

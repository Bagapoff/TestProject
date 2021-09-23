package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class Waiter {

    /**
     * Явное ожидание.
     */
    private static long explicitTimeout;

    static {
        try {
            explicitTimeout = new Long(ParametersProvider
                    .getProperty("wait"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     */
    public static void waitUntilInvisible(
            final WebDriver webDriver,
            final WebElement webElement) {
        new WebDriverWait(webDriver, explicitTimeout).until(ExpectedConditions
                .invisibilityOf(webElement));
    }

    /**
     * Ожидание видимости элемента.
     *
     * @param webDriver  web driver
     * @param webElement элемент на странице
     */
    public static void waitUntilVisible(
            final WebDriver webDriver,
            final WebElement webElement) {
        new WebDriverWait(webDriver, explicitTimeout).until(ExpectedConditions
                .visibilityOf(webElement));
    }

    /**
     * Ожидание видимости списка элементов.
     *
     * @param webDriver   web driver
     * @param webElements список элементы
     */
    public static void waitUntilVisible(
            final WebDriver webDriver,
            final List<WebElement> webElements) {
        new WebDriverWait(webDriver, explicitTimeout).until(ExpectedConditions
                .visibilityOfAllElements(webElements));
    }
}

package tests;

import helpers.ParametersProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.MainPage;

import java.io.IOException;
import java.time.Duration;

public class WildberriesTest {

    /**
     * Web Driver.
     */
    static WebDriver webDriver;

    /**
     * Инициализация драйвера и настройка обраузера.
     *
     * @throws IOException если недоступен файл параметров.
     */
    @BeforeAll
    static void setUp() throws IOException {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(ParametersProvider.getProperty("timeout"))));
        webDriver.manage().window().maximize();
    }

    /**
     * Закрытие драйвера.
     */
    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

    /**
     * Проверка добавления продукта в корзину и удаления его.
     *
     * @throws IOException если не достпуен файл параметров
     */
    @Test
    public void addAndRemoveItemToCartTest() throws IOException {
        webDriver.get(ParametersProvider.getProperty("mainWebUrl"));

        String productName = ParametersProvider.getProperty("productName");
        String expectedEmptyCartMessage =
                ParametersProvider.getProperty("expectedEmptyCartMessage");

        CartPage cartPage = new MainPage(webDriver)
                .findItem(productName)
                .goToItemPage(1)
                .addToCartButtonClick()
                .goToCartPage();
        Assertions.assertTrue(cartPage.getProductTitle().contains(productName),
                "В корзину попал не тот продукт");
        cartPage.deleteProduct();
        Assertions.assertTrue(cartPage.getEmptyCartMessage().contains(expectedEmptyCartMessage),
                "Продукт не был удалён из корзины");
    }
}

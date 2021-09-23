package tests;

import helpers.DriverFactory;
import helpers.ParametersProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.MainPage;

import java.io.IOException;

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
        webDriver = DriverFactory.createDriver();
        webDriver.get(ParametersProvider.getProperty("mainWebUrl"));
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
        String productName = ParametersProvider.getProperty("productName");
        String expectedEmptyCartMessage =
                ParametersProvider.getProperty("expectedEmptyCartMessage");

        CartPage cartPage = new MainPage(webDriver)
                .findItem(productName)
                .goToItemPage(1)
                .addToCartButtonClick()
                .goToCartPage();
        Assertions.assertTrue(cartPage.isProductTitleContains(productName),
                "В корзину попал не тот продукт");
        cartPage.deleteProduct();
        Assertions.assertTrue(cartPage.isEmptyCartMessageContains(expectedEmptyCartMessage),
                "Продукт не был удалён из корзины");
    }
}

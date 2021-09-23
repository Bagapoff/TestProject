package pages;

import helpers.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class CartPage {

    /**
     * Web Driver
     */
    private WebDriver driver;

    /**
     * Навзание продукта.
     */
    @FindBy(css = ".good-info__title")
    private WebElement productTitle;

    /**
     * Кнопка удаления продукта.
     */
    @FindBy(css = ".btn__del")
    private WebElement deleteProductButton;

    /**
     * Сообщение о пустой корзине.
     */
    @FindBy(css = ".basket-empty")
    private WebElement emptyCartMessage;

    /**
     * Конструктор для страницы продукта.
     *
     * @param webDriver драйвер
     */
    public CartPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Проверка названия названия продукта в уорзине.
     *
     * @return boolean
     */
    public boolean isProductTitleContains(final String text) {
        return productTitle.getText().contains(text);
    }

    /**
     * Удаление продукта из корзины.
     *
     * @return CartPage
     * @throws IOException когда файл параметров недоступен
     */
    public CartPage deleteProduct() throws IOException {
        deleteProductButton.click();
        Waiter.waitUntilInvisible(driver, productTitle);
        return this;
    }

    /**
     * Проверка сообщения о пустоте корзины.
     *
     * @return String
     */
    public boolean isEmptyCartMessageContains(final String text) {
        return emptyCartMessage.getText().contains(text);
    }
}

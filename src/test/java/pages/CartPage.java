package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
     * Получение названия продукта.
     *
     * @return String
     */
    public String getProductTitle() {
        return productTitle.getText();
    }

    /**
     * Удаление продукта из корзины.
     *
     * @return CartPage
     */
    public CartPage deleteProduct() {
        deleteProductButton.click();
        return this;
    }

    /**
     * Получение сообщения о пустоте корзины.
     *
     * @return String
     */
    public String getEmptyCartMessage() {
        return emptyCartMessage.getText();
    }
}

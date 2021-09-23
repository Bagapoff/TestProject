package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

    /**
     * Web Driver
     */
    private WebDriver driver;

    /**
     * Кнопка "Добавить в корзину".
     */
    @FindBy(css = ".btn-main")
    WebElement addToCartButton;

    /**
     * Ссылка для перехода в корзину.
     */
    @FindBy(css = ".j-item-basket")
    WebElement goToBasketLink;

    /**
     * Конструктор для страницы продукта.
     *
     * @param webDriver драйвер
     */
    public ItemPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Клик по кнопке "Добавить в корзину".
     *
     * @return ItemPage
     */
    public ItemPage addToCartButtonClick() {
        addToCartButton.click();
        return this;
    }

    /**
     * Переход в корзину.
     *
     * @return CartPage
     */
    public CartPage goToCartPage() {
        new Actions(driver).moveToElement(goToBasketLink).click().perform();
        return new CartPage(driver);
    }
}

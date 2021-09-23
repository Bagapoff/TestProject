package pages;

import helpers.Waiter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class MainPage {

    /**
     * Web Driver
     */
    private WebDriver driver;

    /**
     * Поле для ввода запроса.
     */
    @FindBy(css = ".search-catalog__input")
    private WebElement searchInputField;

    /**
     * Результаты поиска.
     */
    @FindBy(css = "[data-card-index]")
    private List<WebElement> items;

    /**
     * Конструктор для главной страницы
     *
     * @param webDriver драйвер
     */
    public MainPage(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Заполение поисковой строки и подтверждение.
     *
     * @param query запрос(название продукта)
     * @return MainPage
     */
    public MainPage findItem(final String query) {
        searchInputField.sendKeys(query);
        searchInputField.sendKeys(Keys.ENTER);
        return this;
    }

    /**
     * Переход на страницу товара.
     *
     * @param number номер товара в списке результатов поиска
     * @return ItemPage
     * @throws IOException Когда файл параметров не доступен
     */
    public ItemPage goToItemPage(final int number) throws IOException {
        Waiter.waitListOfElementsNotEmpty(driver, items);
        WebElement itemToAdd = items.get(number - 1);
        itemToAdd.click();
        return new ItemPage(driver);
    }
}

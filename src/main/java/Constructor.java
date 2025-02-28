import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

public class Constructor {

    private WebDriver driver;

    //Булки в разделе верхнего меню
    private final By breadUpMenu = By.xpath(".//span[text()='Булки']");
    //Булки в прокручеваемом меню
    private final By breadScrollMenu = By.xpath(".//h2[text()='Булки']");
    //Соусы в разделе верхнего меню
    private final By sauceUpMenu = By.xpath(".//span[text()='Соусы']");
    //Соусы в прокручеваемом меню
    private final By sauceScrollMenu = By.xpath(".//h2[text()='Соусы']");
    //Начинки в разделе верхнего меню
    private final By toppingsUpMenu = By.xpath(".//span[text()='Начинки']");
    //Начинки в прокручеваемом меню
    private final By toppingsScrollMenu = By.xpath(".//h2[text()='Начинки']");
    //Поиск в верхнем меню
    private final By findUpMenu = By.xpath(".//div[contains(@class,'tab_tab__1SPyG')]");
    //Активный пункт меню
    private final By activElementMenu = By.xpath(".//div[contains(@class,'tab_tab_type_current__2BEPc')]");
    //Название пункта меню Булки
    private final String bread = "Булки";
    //Название пункта меню Соусы
    private final String sauce = "Соусы";
    //Название пункта меню Начинки
    private final String toppings = "Начинки";

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание элемента
    public void waitElement(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    //Поиск активного элемента меню и проверка его наименования
    public void selectedActiveElement(String actualText) {
        waitElement(findUpMenu);
        String name = driver.findElement(activElementMenu).getText();
        assertEquals(actualText, name);
    }

    //Выбор проверка текста пункта верхнего меню Булки
    public String selectedBreadElement() {
        waitElement(breadUpMenu);
        driver.findElement(breadUpMenu).click();
        assertEquals(bread, driver.findElement(breadUpMenu).getText());
        return bread;
    }

    //Выбор проверка текста пункта верхнего меню Соусы
    public String selectedSauceElement() {
        waitElement(sauceUpMenu);
        driver.findElement(sauceUpMenu).click();
        assertEquals(sauce, driver.findElement(sauceUpMenu).getText());
        return sauce;
    }

    //Выбор проверка текста пункта верхнего меню Начинки
    public String selectedToppingsElement() {
        waitElement(toppingsUpMenu);
        driver.findElement(toppingsUpMenu).click();
        assertEquals(toppings, driver.findElement(toppingsUpMenu).getText());
        return toppings;
    }

    //Проверка наличия названия раздела Булки в прокручивемом меню
    public void checkBreadScrollMenu() {
        waitElement(breadScrollMenu);
        assertEquals(bread, driver.findElement(breadScrollMenu).getText());
    }

    //Проверка наличия названия раздела Булки в прокручивемом меню
    public void checkSauceScrollMenu() {
        waitElement(sauceScrollMenu);
        assertEquals(sauce, driver.findElement(sauceScrollMenu).getText());
    }

    //Проверка наличия названия раздела Начинки в прокручивемом меню
    public void checkToppingScrollMenu() {
        waitElement(breadScrollMenu);
        assertEquals(bread, driver.findElement(breadScrollMenu).getText());
    }
}
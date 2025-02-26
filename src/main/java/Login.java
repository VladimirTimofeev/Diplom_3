import org.openqa.selenium.By;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;

public class Login {

    private final WebDriver driver;


    //Поле ввода логина
    private final By login = By.xpath(".//input[@name='name']");
    //Поле для ввода пароля
    private final By password = By.xpath(".//input[@name='Пароль']");
    //Кнопка Войти
    private final By buttonEnter = By.className("button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa");
    //Сылка на страницу Зарегестрироваться
    private final By linkRegistration = By.xpath(".//a[text()='Зарегистрироваться']");

    public Login(WebDriver driver) {
        this.driver = driver;
    }
}

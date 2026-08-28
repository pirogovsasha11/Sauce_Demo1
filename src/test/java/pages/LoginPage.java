package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(){
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String user, String password){
        driver.findElement(usernameInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean errorVisible(){
        return driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
    }

    public String getErrorText(){
        return driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
    }
}

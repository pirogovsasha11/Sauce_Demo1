package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProductPage {
    private final By pageTitle = By.xpath("//span[@data-test='title']");

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageTitleVisible(){
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getPageTitle(){
        return driver.findElement(pageTitle).getText();
    }
}

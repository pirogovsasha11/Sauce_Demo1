package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleVisible(){
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getPageTitle(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
}

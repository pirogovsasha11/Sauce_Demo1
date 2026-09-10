package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By shoppingPage = By.id("continue-shopping");
    private final By productList = By.cssSelector(DATA_TEST_PATTERN.formatted("inventory-item-name"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductsName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingPage));

        List<WebElement> allProducts = driver.findElements(productList);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }
}

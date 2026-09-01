package tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce","Epic sadface: Sorry, this user has been locked out." },
                {"","secret_sauce", "Epic sadface: Username is required"},
                {"standard_user","", "Epic sadface: Password is required"},
                {"standard user","secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (dataProvider = "incorrectData", priority = 2)
    public void incorrectDataTestSauceDemo(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message doses not appear");
        assertEquals(errorText,errorMessage, "Error text does not match");
    }

    @Test(description = "Проверка авторизации", priority = 1, invocationCount = 2)
    public void correctUserTestSauceDemo() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");

        boolean pageTitleVisible = productPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productPage.getPageTitle(), "Products", "Error text does not match");
    }
}

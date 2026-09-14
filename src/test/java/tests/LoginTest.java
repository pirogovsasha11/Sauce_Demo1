package tests;

import enums.TitleName;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyUserPermission(), "Epic sadface: Username and password do not match any user in this service"},
                {withEmptyPasswordPermission(), "Epic sadface: Username and password do not match any user in this service"},
                {withErrorPermission(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "incorrectData", priority = 2)
    public void incorrectDataTestSauceDemo(User user, String errorMessage) {
        System.out.println("incorrectDataTestSauceDemo is running in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(user);

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message doses not appear");
        assertEquals(errorText, errorMessage, "Error text does not match");
    }

    @Test(description = "Проверка авторизации", priority = 1, invocationCount = 2)
    public void correctUserTestSauceDemo() {
        System.out.println("correctUserTestSauceDemo is running in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean pageTitleVisible = productPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productPage.getPageTitle(), TitleName.PRODUCTS.getDisplayName(), "Error text does not match");
    }
}

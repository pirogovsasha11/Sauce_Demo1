package tests;

import enums.TitleName;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Sauce-demo project")
@Feature("Авторизация на сайте")
@Owner("Alexander, @Pirogov_1989")
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

    @Story("Ввод невалидных данных пользователя для авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Проверка авторизации с невалидными значениями",dataProvider = "incorrectData", priority = 2)
    public void incorrectDataTestSauceDemo(User user, String errorMessage) {
        loginPage.open();
        loginPage.login(user);

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message doses not appear");
        assertEquals(errorText, errorMessage, "Error text does not match");
    }

    @Story("Ввод валидных данных пользователя для авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Проверка авторизации c валидным значением", priority = 1, invocationCount = 1)
    public void correctUserTestSauceDemo() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean pageTitleVisible = productPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productPage.getPageTitle(), TitleName.PRODUCTS.getDisplayName(), "Error text does not match");
    }
}

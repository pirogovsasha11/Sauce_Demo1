package tests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void lockedUserTestSauceDemo() {
        loginPage.open();
        loginPage.login("locked_out_user","secret_sauce");

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message doses not appear");
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.", "Error text does not match");
    }

    @Test
    public void correctUserTestSauceDemo() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");

        boolean pageTitleVisible = productPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productPage.getPageTitle(), "Products", "Error text does not match");
    }

    @Test
    public void emptyLoginTestSauceDemo() {
        loginPage.open();
        loginPage.login("","secret_sauce");

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible,"Error message doses not appear");
        assertEquals(errorText, "Epic sadface: Username is required", "Error text does not match");
    }

    @Test
    public void emptyPasswordTestSauceDemo(){
        loginPage.open();
        loginPage.login("standard_user","");

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible,"Error message doses not appear");
        assertEquals(errorText, "Epic sadface: Password is required","Error text does not match");
    }

    @Test
    public void incorrectLoginTestSauceDemo(){
        loginPage.open();
        loginPage.login("standard user","secret_sauce");

        boolean isVisible = loginPage.errorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message doses not appear");
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service", "Error text does not match");
    }
}

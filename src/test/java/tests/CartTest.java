package tests;

import enums.TitleName;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

@Epic("Sauce-demo project")
@Feature("Покупательская корзина")
@Owner("Alexander, @Pirogov_1989")
public class CartTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Story("Отображение добаленых товаров в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("PRCY6-60&issueLimit=100")
    @Test(description = "Проверка наличия добавленых товаров в корзине")
    public void checkGoodsInCart() {
        List<String> goodsList =
                List.of("Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Onesie",
                        "Sauce Labs Bike Light",
                        "Sauce Labs Fleece Jacket");
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productPage.isPageTitleVisible());
        assertEquals(productPage.getPageTitle(), TitleName.PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productPage.addGoodsToCart(goodsName);
        }
        productPage.switchToCart();
        soft.assertFalse(cartPage.getProductsName().isEmpty());
        soft.assertEquals(cartPage.getProductsName().size(), 4);
        soft.assertTrue(cartPage.getProductsName().contains("Sauce Labs Bolt T-Shirt"));
        soft.assertTrue(cartPage.getProductsName().contains("Sauce Labs Onesie"));
        soft.assertTrue(cartPage.getProductsName().contains("Sauce Labs Bike Light"));
        soft.assertTrue(cartPage.getProductsName().contains("Sauce Labs Fleece Jacket"));

        soft.assertAll();

    }
}

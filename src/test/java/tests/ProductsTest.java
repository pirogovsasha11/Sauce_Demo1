package tests;

import enums.TitleName;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Sauce-demo project")
@Feature("Страница с наименованием товара")
@Owner("Alexander, @Pirogov_1989")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Onesie",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");


    @Story("Добавление товвров в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Проверка добавления товаров в корзину")
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productPage.isPageTitleVisible());
        assertEquals(productPage.getPageTitle(), TitleName.PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productPage.addGoodsToCart(goodsName);
        }

        productPage.addGoodsToCart(0);
        assertTrue(productPage.isCartBadgeVisible());
        assertEquals(productPage.checkCountersValue(), "5");
        assertEquals(productPage.checkCountersColor(), "rgba(226, 35, 26, 1)");
    }
}

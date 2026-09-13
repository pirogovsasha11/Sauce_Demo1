package tests;

import enums.TitleName;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Onesie",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket");

    @Test()
    public void checkGoodsAdded() {
        System.out.println("checkGoodsAdded is running in thread: " + Thread.currentThread().threadId());

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

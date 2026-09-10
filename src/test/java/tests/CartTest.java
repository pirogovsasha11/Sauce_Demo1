package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Test()
    public void checkGoodsInCart() {
        List<String> goodsList =
                List.of("Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Onesie",
                        "Sauce Labs Bike Light",
                        "Sauce Labs Fleece Jacket");
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productPage.isPageTitleVisible());
        assertEquals(productPage.getPageTitle(), "Products");

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

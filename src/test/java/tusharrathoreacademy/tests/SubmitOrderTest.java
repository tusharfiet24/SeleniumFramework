package tusharrathoreacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tusharrathoreacademy.TestComponents.BaseTest;
import tusharrathoreacademy.pageobjects.CartPage;
import tusharrathoreacademy.pageobjects.CheckoutPage;
import tusharrathoreacademy.pageobjects.ConfirmationPage;
import tusharrathoreacademy.pageobjects.OrderPage;
import tusharrathoreacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> map) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));
		productCatalogue.addProductToCart(map.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplay(map.get("productName")));
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.enterDetails("Ind");
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmText = confirmationPage.getConfirmText();
		Assert.assertTrue(confirmText.trim().equalsIgnoreCase("Thankyou for the order."));
	}

	// To verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("Arjun@gmail.com", "Arjun@1998@");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<>();
		map.put("email", "Arjun@gmail.com");
		map.put("password", "Arjun@1998@");
		map.put("productName", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<>();
		map1.put("email", "Govind@gmail.com");
		map1.put("password", "Govind@1");
		map1.put("productName", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map1 } };
	}
}
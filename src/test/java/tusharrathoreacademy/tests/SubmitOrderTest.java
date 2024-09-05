package tusharrathoreacademy.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import tusharrathoreacademy.TestComponents.BaseTest;
import tusharrathoreacademy.pageobjects.CartPage;
import tusharrathoreacademy.pageobjects.CheckoutPage;
import tusharrathoreacademy.pageobjects.ConfirmationPage;
import tusharrathoreacademy.pageobjects.OrderPage;
import tusharrathoreacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test
	public void submitOrder() throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("Arjun@gmail.com", "Arjun@1998@");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplay(productName));
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
}
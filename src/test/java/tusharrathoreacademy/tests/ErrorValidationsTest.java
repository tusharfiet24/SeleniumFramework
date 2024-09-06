package tusharrathoreacademy.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import tusharrathoreacademy.TestComponents.BaseTest;
import tusharrathoreacademy.TestComponents.Retry;
import tusharrathoreacademy.pageobjects.CartPage;
import tusharrathoreacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPage.loginApplication("Arjun@gmail.com", "Tushar@jkl8@");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email password.");
	}

	@Test()
	public void productErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("Govind@gmail.com", "Govind@1");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.verifyProductDisplay("ZARA COAT 33"));
	}
}
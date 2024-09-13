package tusharrathoreacademy.stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tusharrathoreacademy.TestComponents.BaseTest;
import tusharrathoreacademy.pageobjects.CartPage;
import tusharrathoreacademy.pageobjects.CheckoutPage;
import tusharrathoreacademy.pageobjects.ConfirmationPage;
import tusharrathoreacademy.pageobjects.LandingPage;
import tusharrathoreacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	//TidyGherkin chrome extension- to generate stepdefinition code template
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to the cart$")
	public void i_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplay(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.enterDetails("Ind");
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on Confimation page")
	public void message_is_displayed_on_Confimation_page(String message) {
		String confirmText = confirmationPage.getConfirmText();
		Assert.assertTrue(confirmText.trim().equalsIgnoreCase("Thankyou for the order."));
		tearDown();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String message) {
		Assert.assertEquals(landingPage.getErrorMessage(), message);
		tearDown();
	}
}

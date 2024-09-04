package tusharrathoreacademy;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import tusharrathoreacademy.pageobjects.CartPage;
import tusharrathoreacademy.pageobjects.CheckoutPage;
import tusharrathoreacademy.pageobjects.ConfirmationPage;
import tusharrathoreacademy.pageobjects.LandingPage;
import tusharrathoreacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
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
		driver.quit();
	}
}
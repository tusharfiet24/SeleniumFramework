package tusharrathoreacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tusharrathoreacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cart h3")
	private List<WebElement> productTitles;
	
	By productsCart = By.cssSelector(".cart h3");

	public Boolean verifyProductDisplay(String productName) {
		waitForElementToAppear(productsCart);
		Boolean match = productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckOut(){
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}

package tusharrathoreacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tusharrathoreacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results button")
	List<WebElement> selectCountries;
	
	@FindBy(css = ".action__submit")
	WebElement submit;

	public CheckoutPage enterDetails(String counName) {
		country.sendKeys(counName);
		return this;
	}

	public void selectCountry(String countryName) {
		WebElement selectCountry = selectCountries.stream()
				.filter(selCountry -> selCountry.getText().trim().equals(countryName)).findFirst()
				.orElseGet(null);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		scrollToWebElement(submit);
		submit.click();
		return new ConfirmationPage(driver);
	}

}

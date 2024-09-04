package tusharrathoreacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(css = "[type='password']")
	WebElement userPassword;

	@FindBy(name = "login")
	WebElement submit;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

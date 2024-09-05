package tusharrathoreacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tusharrathoreacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	By productsCart = By.cssSelector(".cart h3");

	public Boolean verifyOrderDisplay(String order) {
		Boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equals(order));
		return match;
	}

}

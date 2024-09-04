package tusharrathoreacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tusharrathoreacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirm;
	
	@FindBy(css = "label.ng-star-inserted")
	WebElement order;
	
	public String getConfirmText() {
		return confirm.getText().trim();
	}
	
	public String getOrderId() {
		return order.getText().split(" ")[1];
	}
}

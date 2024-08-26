package tusharrathoreacademy;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("Arjun@gmail.com");
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("Arjun@1998@");
		driver.findElement(By.name("login")).click();
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//Java Stream
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".w-10")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		
		Boolean match =  cartProducts.stream().anyMatch(cartProduct -> 
		cartProduct.getText().equals(productName));
		
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ind");
		
		List<WebElement> selectCountries = driver.findElements(By.cssSelector(".ta-results button"));
		WebElement country = selectCountries.stream().filter(selectCountry -> 
		selectCountry.getText().trim().equals("India")).findFirst().orElseGet(null);
		country.click();
		
		WebElement ele = driver.findElement(By.cssSelector(".action__submit"));
		
		Actions action = new Actions(driver);
		action.scrollToElement(ele).build().perform();
		action.click(ele).build().perform();
		
		String confirmText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmText.trim().equalsIgnoreCase("Thankyou for the order."));
		
		String orderId = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
		System.out.println(orderId.split(" ")[1]);
		
		driver.quit();
	}

}

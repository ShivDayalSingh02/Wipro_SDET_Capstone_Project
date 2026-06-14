package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.TestData;
import utilities.WaitUtils;

public class NewCustomerPage {

    WebDriver driver;

    public NewCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    By newCustomerLink = By.linkText("New Customer");

    By customerName = By.name("name");
    By gender = By.xpath("//input[@value='m']");
    By dob = By.name("dob");
    By address = By.name("addr");
    By city = By.name("city");
    By state = By.name("state");
    By pin = By.name("pinno");
    By mobile = By.name("telephoneno");
    By email = By.name("emailid");
    By password = By.name("password");

    By submitBtn = By.name("sub");

    public void addCustomer() {

    	WaitUtils.waitForElement(
    	        driver,
    	        newCustomerLink)
    	        .click();

        driver.findElement(customerName)
                .sendKeys("Shiv");

        driver.findElement(gender).click();

        driver.findElement(dob)
                .sendKeys("02-10-2001");

        driver.findElement(address)
                .sendKeys("Sector 52");

        driver.findElement(city)
                .sendKeys("Noida");

        driver.findElement(state)
                .sendKeys("Delhi");

        driver.findElement(pin)
                .sendKeys("201307");

        driver.findElement(mobile)
                .sendKeys("9876543210");

        driver.findElement(email)
                .sendKeys("user"
                        + System.currentTimeMillis()
                        + "@gmail.com");

        driver.findElement(password)
                .sendKeys("Test123");

        WaitUtils.waitForElement(
                driver,
                submitBtn)
                .click();
        
        String customerId =
        		driver.findElement(
        		By.xpath("//td[text()='Customer ID']/following-sibling::td"))
        		.getText();

        		TestData.customerId = customerId;

        		System.out.println(
        		"Generated Customer ID = "
        		+ customerId);
    }
}
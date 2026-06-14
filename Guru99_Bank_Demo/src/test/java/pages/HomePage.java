package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By managerText =
            By.xpath("//*[contains(text(),'Manger Id')]");

    public boolean verifyManagerHomePage() {

        try {

            return driver.findElement(managerText)
                    .isDisplayed();

        } catch (Exception e) {

            System.out.println(
                    "Manager Id element not found");

            return false;
        }
    }
}
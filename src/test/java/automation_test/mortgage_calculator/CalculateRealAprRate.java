package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page_objects.NavigationBar;
import parameters.DataProviderClass;

public class CalculateRealAprRate {

    WebDriver driver;


    @BeforeMethod
    public void browserInitialization(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browserActions(driver).openBrowser("https://www.mortgagecalculator.org/");
    }
    @Test
    public void calculateRealApr(){
      new NavigationBar(driver)
              .mouseHoverToRates()
              .navigatesToRealApr()
              .waitForPageToLoad()
              .typeHomePrice("200000")
              .clickDownPaymentInDollar()
              .typeDownPayment("15000")
              .typeInterestRate("3")
              .typeYearDuration("30")
              .clickOnCalculateButton()
              .validatingRealAprRate("3.130%");

    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
        ActOn.browserActions(driver).close();
    }


}

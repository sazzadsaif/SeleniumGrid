package database;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.NavigationBar;
import parameters.DataProviderClass;

public class CalculateRealAprRate_database {

    WebDriver driver;
    Select select;

    @BeforeMethod
    public void browserInitialization(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browserActions(driver).openBrowser("https://www.mortgagecalculator.org/");
    }
    @Test(dataProvider="RealAprRates",dataProviderClass = DataProviderClass.class)
    public void calculateRealApr(String homePrice,String downPayment,String intrate,String year, String realAprRate){
      new NavigationBar(driver)
              .mouseHoverToRates()
              .navigatesToRealApr()
              .waitForPageToLoad()
              .typeHomePrice(homePrice)
              .clickDownPaymentInDollar()
              .typeDownPayment(downPayment)
              .typeInterestRate(intrate)
              .typeYearDuration(year)
              .clickOnCalculateButton()
              .validatingRealAprRate(realAprRate);

    }
    @AfterMethod
    public void closeBrowser(){

        driver.quit();
        ActOn.browserActions(driver).close();
    }


}

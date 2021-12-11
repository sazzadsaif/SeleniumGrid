package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.NavigationBar;
import utilities.DateUtils;
import utilities.ReadConfigFiles;
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateMortgageRate {

    private static final Logger LOGGER = LogManager.getLogger(CalculateMortgageRate.class);

    WebDriver driver;

    @BeforeMethod
    public void browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        LOGGER.info("------------------Test Name: Calculate monthly payment-----------");

        ActOn.browserActions(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

    @Test
    public void calculateMonthlyPayment(){
        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];


        new NavigationBar(driver)
                        .navigateToHome()
                        .typeHomePrice("300000")
                        .typeDownPayment("6000")
                        .clickDownPaymentInDollar()
                        .typeTheLoanAmount("240000")
                        .typeInterestRate("3")
                        .typeLoanTermYears("30")
                        .selectMonth(month)
                        .selectYear(year)
                        .typePropertyTax("5000")
                        .typePMI("0.5")
                        .typeHomeIns("1000")
                        .typeHOA("100")
                        .selectLoanType("FHA")
                        .selectBuyOrRefiOption("Buy")
                        .clickOnTheCalculateButton()
                        .validateTotalMonthlyPayment("$1,611.85");

            }

            @AfterMethod
            public void closeBrowser(){
            LOGGER.info("--------End Test Case---------");
          //driver.quit();
           ActOn.browserActions(driver).close();
    }
}

package database;

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

public class CalculateMortgageRate_database {

    private static final Logger LOGGER = LogManager.getLogger(CalculateMortgageRate_database.class);

    WebDriver driver;

    @BeforeMethod
    public void browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("------------------Test Name: Calculate monthly payment-----------");
        String browseUrl = ReadConfigFiles.getPropertyValues("Url");

        ActOn.browserActions(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

    @Test
    public void calculateMonthlyPayment(){
        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];



        try {
            ResultSet rs = SqlConnector.readData("select * from monthly_mortgage");
            while (rs.next()) {

                new NavigationBar(driver)
                        .navigateToHome()
                        .typeHomePrice(rs.getString("homevalue"))
                        .typeDownPayment(rs.getString("downpayment"))
                        .clickDownPaymentInDollar()
                        .typeTheLoanAmount(rs.getString("loanamaount"))
                        .typeInterestRate(rs.getString("interestrate"))
                        .typeLoanTermYears(rs.getString("loanterm"))
                        .selectMonth(month)
                        .selectYear(year)
                        .typePropertyTax(rs.getString("propertytax"))
                        .typePMI(rs.getString("pmi"))
                        .typeHomeIns(rs.getString("homeinsurance"))
                        .typeHOA(rs.getString("hoa"))
                        .selectLoanType(rs.getString("loantype"))
                        .selectBuyOrRefiOption(rs.getString("buyorrefi"))
                        .clickOnTheCalculateButton()
                        .validateTotalMonthlyPayment(rs.getString("totalmonthlypayment"));

            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }
    @AfterMethod
    public void closeBrowser(){
        LOGGER.info("--------End Test Case---------");
        //driver.quit();
        ActOn.browserActions(driver).close();
    }
}

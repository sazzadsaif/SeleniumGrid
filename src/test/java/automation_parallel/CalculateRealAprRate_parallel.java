package automation_parallel;


import org.testng.annotations.Test;
import page_objects.NavigationBar;

public class CalculateRealAprRate_parallel extends BaseClass {

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

}

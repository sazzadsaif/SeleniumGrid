package automation_parallel;


import org.testng.annotations.Test;
import page_objects.NavigationBar;
import utilities.DateUtils;

public class CalculateMortgageRate_parallel extends BaseClass {

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

}

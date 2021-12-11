package parameters;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name ="RealAprRates")
    public Object[][] storeRealAprRatesData(){
        return new Object[][]{
                {"200000","15000","3","30","3.130%"}
        };
    }
}

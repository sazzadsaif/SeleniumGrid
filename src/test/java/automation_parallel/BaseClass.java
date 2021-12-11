package automation_parallel;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.DriverFactory;

public class BaseClass {
    public WebDriver driver;
    Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    String testCaseName = String.format("--------Test: %s---------", this.getClass().getName());
    String endTestCase = String.format("-------Test End: %s",this.getClass().getName());


    @BeforeClass
    public void addThread(){
        ThreadContext.put("threadName",this.getClass().getName());
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver = DriverFactory.getInstance().getDriver();
    }

    @BeforeMethod
    public void browserInitialization(){
        LOGGER.info(testCaseName);
        ActOn.browserActions(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

    @AfterMethod
    public void closeBrowser(){
        DriverFactory.getInstance().removeDriver();
        LOGGER.info(endTestCase);
        //ActOn.browserActions(driver).close();

    }
}

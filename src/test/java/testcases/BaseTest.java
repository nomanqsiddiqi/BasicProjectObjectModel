package testcases;

import base.Page;
import org.testng.annotations.AfterSuite;

public class BaseTest {

    @AfterSuite
    public void tearDown(){
            new Page().report.flush();
            new Page().quit();
    }


}

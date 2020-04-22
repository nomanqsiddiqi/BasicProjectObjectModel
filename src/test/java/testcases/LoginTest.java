package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.Utilities;

import java.util.Hashtable;

public class LoginTest extends BaseTest{
    @Test(dataProviderClass = Utilities.class, dataProvider = "dp")
    public  void loginTest(Hashtable<String, String > data){
        // This is login Test under the test cases folder
        HomePage homePage = new HomePage();
        homePage.goToLogin().doLogin(data.get("username"), data.get("password"));
    }
}

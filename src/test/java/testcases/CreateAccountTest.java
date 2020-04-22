package testcases;

import base.Page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ZohoAppPage;
import utilities.Utilities;

import java.util.Hashtable;

public class CreateAccountTest extends BaseTest {
    //This is CreateAccountTest class
    @Test(dataProviderClass = Utilities.class, dataProvider = "dp")
    public void createAccountTest(Hashtable<String, String> data) {
        new ZohoAppPage().goToCRM();
        Page.topMenu.gotoAccount().goToCreateAccount().AddAccountInfo(data.get("accountName"));
    }
}

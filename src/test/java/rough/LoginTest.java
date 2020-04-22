package rough;

import base.Page;
import pages.HomePage;

public class LoginTest {
    public static void main(String[] args) {
        // This is test case is located under the rough package
        HomePage homePage = new HomePage();
        homePage.goToLogin().doLogin("noman90@hotmail.com", "Selenium@123").goToCRM();
        Page.topMenu.gotoAccount().goToCreateAccount().AddAccountInfo("Jacob");
    }
}

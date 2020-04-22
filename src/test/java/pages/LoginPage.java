package pages;

import base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public ZohoAppPage doLogin(String username, String password){
        type("username_XPATH", username);
        click("nextbtn_XPATH");
        type("password_CSS", password);
        click("signin_XPATH");
        return new ZohoAppPage();
    }
    public void usingGoogle_ToLogin(){

    }
    public void usingMiscrosoft_ToLogin(){

    }
    public void usingLinkedin_ToLogin(){

    }
    public void usingFacebook_ToLogin(){

    }
    public void usingTwitter_ToLogin(){

    }
    public void usingMore_ToLogin(){

    }
}

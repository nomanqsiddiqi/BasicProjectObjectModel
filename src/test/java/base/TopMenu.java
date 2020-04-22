package base;

import org.openqa.selenium.WebDriver;
import pages.crm.accounts.AccountsPage;

public class TopMenu {
    public WebDriver driver;
    public TopMenu(WebDriver driver){
        this.driver = driver;
    }
    public void gotoHome(){

    }
    public void gotoContacts(){

    }
    public void gotoLeads(){

    }
    public AccountsPage gotoAccount(){
        new Page().click("accountpage_XPATH");
        return new AccountsPage();
    }
    public void gotoDeals(){

    }
    public void signOut(){

    }
}

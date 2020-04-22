package pages.crm.accounts;

import base.Page;
import org.openqa.selenium.By;

public class AccountsPage extends Page {

    public CreateAccountPage goToCreateAccount(){
        click("createaccount_CSS");
        return new CreateAccountPage();
    }
    public void gotoImportAccounts(){

    }

}

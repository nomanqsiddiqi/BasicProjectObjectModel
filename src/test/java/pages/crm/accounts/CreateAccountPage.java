package pages.crm.accounts;

import base.Page;

public class CreateAccountPage extends Page {
    public void AddAccountInfo(String accountName){
        type("accountname_CSS", accountName);
    }
}
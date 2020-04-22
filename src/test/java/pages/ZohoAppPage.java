package pages;

import base.Page;
import base.TopMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {
    // This is ZohoAppPage class
    public void goToBooks() {
        driver.findElement(By.xpath("//a[@href='https://books.zoho.com/Home.jsp']/div[@class='app-nm']")).click();
    }
    public void goToCalendar(){
        driver.findElement(By.xpath("//a[@href='http://calendar.zoho.com/mycalendar']/div[@class='app-nm']")).click();
    }
    public void goToCampaigns(){
        driver.findElement(By.xpath("//a[@href='https://campaigns.zoho.com/start.do']/div[@class='app-nm']")).click();
    }
    public void goToCliq(){
        driver.findElement(By.xpath("//a[@href='https://cliq.zoho.com/mpchat.do']/div[@class='app-nm']")).click();
    }
    public void goToCRM() {
       click("crmlink_XPATH");
    }
}

package pages;
import base.Page;
import org.openqa.selenium.By;
public class HomePage extends Page {

    public void goToSignUp() {
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/a[5]")).click();
    }
    public LoginPage goToLogin(){
        click("loginPage_XPATH");
        return new LoginPage();
    }
    public void goToResource(){
        driver.findElement(By.linkText("Resources")).click();
    }
    public void goToCustomers(){
        driver.findElement(By.linkText("Customers")).click();
    }
    public void goToSolutions(){
        driver.findElement(By.xpath("//li[@class='expanded dropdown zmenu-solutions active']/span[@class='dropdown-toggle nolink ']")).click();
    }
    public void goToPricing(){
        driver.findElement(By.linkText("Pricing")).click();
    }
    public void goToFeatures(){
        driver.findElement(By.xpath("//li[@class='leaf first zmenu-features expanded dropdown active']/a[.='Features']")).click();
    }
    public void validateFooterLinks(){

    }
}

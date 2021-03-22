package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage extends AbstractPage{
    private WebElement username;

    private WebElement password;

    private WebElement submit;


    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static LoginPage openLogin(WebDriver driver) {
        get(driver, "/login"); //driver da napravi povik kon 9998/login
        System.out.println(driver.getCurrentUrl());//proverka na tekovnoto url na koe se naogame
        return PageFactory.initElements(driver, LoginPage.class);

    }
    public static CoursePage doLogin(WebDriver driver, LoginPage loginPage, String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        driver.findElement(By.cssSelector(".btn")).click();
        //loginPage.submit.click(); //koga ke klikneme submit odime na course straaa
        System.out.println(driver.getCurrentUrl());//ispecati dali sme na course page
        return PageFactory.initElements(driver, CoursePage.class); // vrati ja course page
    }

    public static LoginPage logout(WebDriver driver) {
        get(driver, "/logout");
        return PageFactory.initElements(driver, LoginPage.class);
    }

}

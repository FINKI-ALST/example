package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditCourse extends AbstractPage{
    private WebElement name;
    private WebElement description;
    private WebElement id;
    private WebElement typestatus;
    private WebElement submit;
    public static CoursePage addCourse(WebDriver driver, String name, String description, String teacher, String typeStatus)
    {
        get(driver,"/courses/add-form");
        AddOrEditCourse addOrEditCourse= PageFactory.initElements(driver,AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.id.click();
        addOrEditCourse.id.findElement(By.xpath("//option[. = '"+teacher+"']")).click();
        addOrEditCourse.typestatus.click();
        addOrEditCourse.typestatus.findElement(By.xpath("//option[. ='"+typeStatus+"']")).click();
        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver,CoursePage.class);

    }
    public AddOrEditCourse(WebDriver driver) {
        super(driver);
    }
}

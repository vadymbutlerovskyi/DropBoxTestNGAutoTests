package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import tests.BaseTest;
 
public class PageGenerator {
 
    public WebDriver driver;

    public PageGenerator(WebDriver driver){
        this.driver = driver;
    }
 
    public  <TPage extends BaseTest> TPage GetInstance (Class<TPage> pageClass) {
        try {
            //Initialize the Page with its elements and return it.
            return PageFactory.initElements(driver,  pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
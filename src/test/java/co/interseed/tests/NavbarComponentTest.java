package co.interseed.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class NavbarComponentTest extends ParentClass {
    @Test
    public void LoginTest() throws InterruptedException {
        driver.get(url);
        checkCurrentUrlToBe(url + "/login");
        driver.findElement(By.id("email")).sendKeys("dev@interseed.co");
        driver.findElement(By.id("password")).sendKeys("asdasdasd");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.urlToBe(url + "/dashboard"));
        checkCurrentUrlToBe(url + "/dashboard");
    }

    @Test(dependsOnMethods = {"LoginTest"})
    public void linksTest() throws InterruptedException {
        driver.get(url);
        String expectedUrl = url + "/dashboard";
        checkCurrentUrlToBe(expectedUrl);

        driver.findElement(By.xpath("//img[@alt='Interseed Logo']")).click();
        checkCurrentUrlToBe(expectedUrl);

        driver.findElement(By.linkText("Dashboard")).click();
        checkCurrentUrlToBe(url + "/dashboard");

        driver.findElement(By.linkText("Landscapes")).click();
        checkCurrentUrlToBe(url + "/landscapes");

        driver.findElement(By.linkText("Opportunities")).click();
        driver.findElement(By.linkText(("Funding"))).click();
        checkCurrentUrlToBe(url + "/funding");

        driver.findElement(By.linkText("Opportunities")).click();
        driver.findElement(By.linkText(("Jobs"))).click();
        checkCurrentUrlToBe(url + "/jobs");

        driver.findElement(By.linkText("Resources")).click();
        checkCurrentUrlToBe(url + "/resources");

        driver.findElement(By.linkText("About")).click();
        checkCurrentUrlToBe(url + "/about");
    }

    @Test
    public void imagesTest() {
        int numberImagesBroken = 0;
        List<WebElement> imageList = driver.findElements(By.tagName("img"));
        for (WebElement img : imageList) {
            if (img != null) {
                if (img.getAttribute("naturalWidth").equals("0")) {
                    numberImagesBroken++;
                }
            }
        }
        Assert.assertEquals(numberImagesBroken, 0);
    }

}

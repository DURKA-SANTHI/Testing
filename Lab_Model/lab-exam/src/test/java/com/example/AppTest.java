package com.example;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    @BeforeMethod
    public void bt()
    {
        WebDriverManager.edgedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://www.firstcry.com/");

        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("C:\\Users\\91866\\OneDrive\\Desktop\\SELENIUM\\Lab_Model\\report.html");
        report=new ExtentReports();
        report.attachReporter(sparkReporter);
    }
    @Test
    public void f1()throws Exception
    {
    test=report.createTest("test1", "testing description");
    FileInputStream fs=new FileInputStream("C:\\Users\\91866\\OneDrive\\Desktop\\SELENIUM\\Lab_Model\\labexcel.xlsx");
    @SuppressWarnings("resource")
    XSSFWorkbook wb=new XSSFWorkbook(fs);
    XSSFSheet sheet=wb.getSheetAt(0);
    String d1=sheet.getRow(0).getCell(0).getStringCellValue();
    WebElement a=driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
    a.click();
    Thread.sleep(2000);
    a.sendKeys(d1);
    Thread.sleep(3000);
    a.sendKeys(Keys.ENTER);
    Thread.sleep(3000);
    String cur=driver.getCurrentUrl();
    if(cur.contains("kids-toys"))
    System.out.println("Validation successful");
    else
    System.out.println("Validation unsuccessful");
    driver.quit();
}
@Test
public void f2()throws Exception
{
    test=report.createTest("test2", "testing description");
    FileInputStream fs=new FileInputStream("C:\\Users\\91866\\OneDrive\\Desktop\\SELENIUM\\Lab_Model\\labexcel.xlsx");
    @SuppressWarnings("resource")
    XSSFWorkbook wb=new XSSFWorkbook(fs);
    XSSFSheet sheet=wb.getSheetAt(0);
    String d2=sheet.getRow(0).getCell(1).getStringCellValue();
    WebElement a= driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
    a.click();
    Thread.sleep(2000);
    a.sendKeys(d2);
    Thread.sleep(3000);
    a.sendKeys(Keys.ENTER);
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]/a/div/span[1]")).click();
    Thread.sleep(3000);
    WebElement lab=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1"));
    if(lab.isDisplayed())
    {
        System.out.println(" label element is present");
    }
    else
        System.out.println(" label element is not found");
    driver.close();
}
@Test
public void f3()throws Exception
{
    test=report.createTest("test3", "testing description");
    FileInputStream fs=new FileInputStream("C:\\Users\\91866\\OneDrive\\Desktop\\SELENIUM\\Lab_Model\\labexcel.xlsx");
    @SuppressWarnings("resource")
    XSSFWorkbook wb=new XSSFWorkbook(fs);
    XSSFSheet sheet=wb.getSheetAt(0);
    String d3=sheet.getRow(0).getCell(2).getStringCellValue();
    WebElement a= driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
    a.click();
    Thread.sleep(2000);
    a.sendKeys(d3);
    Thread.sleep(3000);
    a.sendKeys(Keys.ENTER);
    Thread.sleep(3000);
    String tit=driver.getTitle();
    System.out.println(tit);
    driver.close();
}
@AfterMethod
    public void am(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(Status.FAIL,"testcase failed:"+result.getName());
            test.log(Status.FAIL, "testcase failed reason:"+result.getThrowable());
            File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path="C:\\Users\\91866\\OneDrive\\Desktop\\SELENIUM\\Lab_Model";
            FileUtils.copyFile(file,new File(path));
            test.addScreenCaptureFromPath(path);
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            test.log(Status.PASS,"Testcase passed: "+result.getName());
        }
        else if(result.getStatus()==ITestResult.SKIP)
        {
             test.log(Status.SKIP,"testcase skipped:"+result.getName());
        }
    }

    @AfterTest
    public void aft()
    {
    
    report.flush();
    }

}

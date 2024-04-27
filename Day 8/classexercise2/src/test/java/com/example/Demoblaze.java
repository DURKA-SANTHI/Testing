package com.example;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Demoblaze {
    WebDriver driver;
    @BeforeMethod
    public void meth() throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        Thread.sleep(2000);
    }
    @Test
    public void test1() throws InterruptedException
    {
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("MacBook air")).click();
        Thread.sleep(3000);
        String prod = driver.findElement(By.xpath("//*[@id='tbodyid']/h2")).getText();
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        driver.findElement(By.linkText("Cart")).click();
        Thread.sleep(3000);
        String name = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
        if(prod.equals(name))
        {
            System.out.println("Test 1 Passed");
        }
        else{
            System.out.println("Test 1 Failed");
        }
        Thread.sleep(5000);
    }
    @Test
    public void test2() throws Exception
    {
       
        FileInputStream fs = new FileInputStream("D:\\s2d.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String uname = sheet.getRow(1).getCell(0).getStringCellValue();
        String pass = sheet.getRow(1).getCell(1).getStringCellValue();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='login2']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='loginusername']")).sendKeys(uname);
        driver.findElement(By.xpath("//*[@id='loginpassword']")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
        Thread.sleep(6000);
        String res = driver.findElement(By.xpath("//*[@id=\"nameofuser\"]")).getText();
        if(res.contains("Welcome"))
        System.out.print("Successful Login");
        else
        System.out.print("Login failed");


    }

    @AfterMethod
    public void meth1()
    {
        driver.quit();
    }
}

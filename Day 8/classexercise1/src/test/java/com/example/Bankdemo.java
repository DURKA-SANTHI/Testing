package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Bankdemo {
     WebDriver driver;

    @BeforeMethod
    public void BeforeMethod() throws Exception
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    
    @Test(dataProvider = "dat")
    public void test1(String name,String pass) throws Exception
    {
        driver.findElement(By.id("username")).sendKeys(name);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        String cur=driver.getCurrentUrl();
        //String home="http://dbankdemo.com/bank/home";
        Thread.sleep(2000);
        //assertEquals(cur,home);
        if(cur.equals("http://dbankdemo.com/bank/home"))
        {
            System.out.println("Passed Test 1");
        }
        else{
            System.out.println("Failed Test 1");
        }
    }
    
    @Test(dataProvider = "dat")
    public void test2(String name,String pass)throws Exception
    {
        driver.findElement(By.id("username")).sendKeys(name);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Deposit")).click();
        Thread.sleep(3000);
        Select S=new Select(driver.findElement(By.id("selectedAccount")));
        S.selectByIndex(1);
        Thread.sleep(3000);
        driver.findElement(By.id("amount")).sendKeys("5000");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(3000);
        JavascriptExecutor JS=(JavascriptExecutor)driver;
        JS.executeScript("window.scrollTo(0,2000)", "");
        Thread.sleep(3000);
        String amount=driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        Thread.sleep(3000);
        //assertEquals(amount, "$5000.00");
        if(amount.equals("$5000.00"))
        {
            System.out.println("Passed Test 2");
        }
        else{
            System.out.println("Failed Test 2");
        }
    }
    
    @Test(dataProvider = "dat")
    public void test3(String name,String pass)throws Exception
    {
        driver.findElement(By.id("username")).sendKeys(name);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Withdraw")).click();
        Thread.sleep(3000);
        Select S=new Select(driver.findElement(By.id("selectedAccount")));
        S.selectByIndex(1);
        Thread.sleep(3000);
        driver.findElement(By.id("amount")).sendKeys("3000");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(3000);
        JavascriptExecutor JS=(JavascriptExecutor)driver;
        JS.executeScript("window.scrollTo(0,2000)", "");
        Thread.sleep(3000);
        String amounts=driver.findElement(By.xpath("//*[@id=\"transactionTable\"]/tbody/tr[1]/td[4]")).getText();
        Thread.sleep(3000);
        //assertEquals(amount, "$-3000.00");
        if(amounts.equals("$-3000.00"))
        {
            System.out.println("Passed Test 3");
        }
        else{
            System.out.println("Failed Test 3");
        }
    }

    @DataProvider(name = "dat")
    public String[][] datas()
    {
        String a[][]=new String[1][2];
        a[0][0]="S@gmail.com";
        a[0][1]="P@ssword12";
        return a;
    }
    @AfterMethod
    public void AfterMethod()
    {
        driver.quit();
    }

}

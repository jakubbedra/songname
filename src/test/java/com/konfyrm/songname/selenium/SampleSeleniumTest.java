package com.konfyrm.songname.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class SampleSeleniumTest {

    @Test
    public void sampleTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:63342/songname/ui/author_list/author_list.html");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = driver.findElement(By.id("name"));
        element.sendKeys("reltih floda");
        WebElement addButton = driver.findElement(By.id("addAuthor"));

        Assertions.assertTrue(element.isDisplayed());
        Assertions.assertTrue(addButton.isDisplayed());

        addButton.click();

        Thread.sleep(1 * 1000);

        //cleaning up
        WebElement trash = driver.findElement(By.id("tableBody"))
                .findElements(By.tagName("tr"))
                .stream()
                .filter(e -> {
                    List<WebElement> rowToDelete = e.findElements(By.tagName("td"))
                            .stream()
                            .filter(e2 -> e2.getText().equals("reltih floda"))
                            .collect(Collectors.toList());
                    return !rowToDelete.isEmpty();
                })
                .collect(Collectors.toList())
                .get(0)
                .findElement(By.tagName("button"));

        Assertions.assertTrue(trash.isDisplayed());
        trash.click();

        Thread.sleep(1 * 1000);

        driver.quit();
    }

}

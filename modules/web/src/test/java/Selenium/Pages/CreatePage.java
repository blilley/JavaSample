package Selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePage
{
    private final int waitDelay = 10;
    private final WebDriverWait wait;
    private WebDriver driver;

    public CreatePage(WebDriver driver)
    {
        this.driver = driver;

        if (!isVisible()) {
            throw new IllegalStateException("This is not the Create Newspaper Page");
        }

        wait = new WebDriverWait(driver, waitDelay);
    }

    public boolean isVisible()
    {
        return "Open Periodical - Create Newspaper".equals(driver.getTitle());
    }

    public ListPage createNewspaper(String newsPaperName)
    {
        WebElement titleInput = driver.findElement(By.cssSelector("#title"));
        WebElement createButton = driver.findElement(By.cssSelector("#createNewspaper"));

        titleInput.clear();
        titleInput.sendKeys(newsPaperName);
        createButton.click();

        wait.until(ExpectedConditions.stalenessOf(createButton));

        return new ListPage(driver);
    }
}

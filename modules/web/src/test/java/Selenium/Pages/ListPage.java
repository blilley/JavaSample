package Selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPage
{
    private final WebDriver driver;
    private final WebDriverWait wait;

    private int waitDelay = 10;

    private By table = By.className("table");
    private By create = By.cssSelector("#createNewspaper");
    private String deleteButtonSelector = "//tr[td[contains(text(), '${newspaperName}')]]//a";


    public ListPage(WebDriver driver)
    {
        if (!"Open Periodical".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the List Page");
        }

        this.driver = driver;
        wait = new WebDriverWait(driver, waitDelay);
    }

    public ListPage deleteNewspaper(String newspaperName)
    {
        WebElement newsPaper1DeleteElement = driver.findElement(table).findElement(By
                .xpath(deleteButtonSelector.replace("${newspaperName}", newspaperName)));

        newsPaper1DeleteElement.click();

        return new ListPage(driver);
    }

    public Map<String, String> getTableRows()
    {
        WebElement baseTable = wait.until(ExpectedConditions.presenceOfElementLocated(table));
        By tableRowTag = By.tagName("tr");
        List<WebElement> tableRows = baseTable.findElements(tableRowTag);

        Map<String, String> tableData = new HashMap<>();

        for (WebElement row : tableRows)
        {
            String tdTag = "./*";
            List<WebElement> cells = row.findElements(By.xpath(tdTag));

            tableData.put(cells.get(0).getText(), cells.get(1).getText());
        }

        return tableData;
    }

    public CreatePage createNewsPaper()
    {
        WebElement createButton = driver.findElement(create);
        createButton.click();

        wait.until(ExpectedConditions.stalenessOf(createButton));

        return new CreatePage(driver);
    }
}

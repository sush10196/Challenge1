package Briq;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;

public class TableDataExtractor {
    public static void main(String[] args) {
      
        
        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the local file URL
          driver.get("http://the-internet.herokuapp.com/challenging_dom");

        // Find the table element by its XPath
        WebElement table = driver.findElement(By.xpath("//table"));

        // Extract table data into a List of Lists (String)
        List<List<String>> tableData = new ArrayList<>();
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        
        for (WebElement row : rows) {
            List<String> rowData = new ArrayList<>();
            List<WebElement> columns = row.findElements(By.tagName("td"));
            
            for (WebElement column : columns) {
                rowData.add(column.getText());
            }
            
            tableData.add(rowData);
        }

        // Print the table data
        for (List<String> rowData : tableData) {
            for (String cellData : rowData) {
                System.out.print(cellData + "\t");
            }
            System.out.println();
        }
        
        // Close the WebDriver
       // driver.quit();
    }
}

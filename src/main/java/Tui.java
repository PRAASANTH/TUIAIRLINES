import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tui {
	public static String getData(int rowNo, int cellno) throws Throwable {
		String v = null;
		File loc = new File("C:\\Users\\Prasanth\\eclipse-workspace\\TuiAirlines\\excel\\Tui.xlsx");
		FileInputStream stream = new FileInputStream(loc);
		Workbook w = new XSSFWorkbook(stream);
		Sheet s = w.getSheet("Sheet1");
		Row r = s.getRow(rowNo);
		Cell c = r.getCell(cellno);
		v = c.getStringCellValue();
		return v;
	}

	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prasanth\\eclipse-workspace\\TuiAirlines\\Driver\\chromedriver.exe");
		WebDriver Driver = new ChromeDriver();
		
		Driver.get("https://www.tui.co.uk/");
		Driver.manage().window().maximize();
		
		WebElement flightdrop = Driver.findElement(By.xpath("//span[text()='FLIGHTS']"));
		
		Actions acc = new Actions(Driver);
		acc.moveToElement(flightdrop).perform();
		
		WebElement cheap = Driver.findElement(By.xpath("//a[text()='Cheap Flights'][1]"));
		cheap.click();
		
		
		Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		Driver.findElement(By.xpath("//div[@class='section-overlay']")).click();
		
		Driver.findElement(By.xpath("//li[@data-value='oneWay']")).click();
		
		WebElement from = Driver.findElement(By.xpath("//div[@id='wherefromPlaceholder']"));
		from.click();
		
		WebElement fromsearch = Driver.findElement(By.xpath("//li[@id='LGW']"));
		fromsearch.click();
		
		Driver.findElement(By.xpath("//div[text()='Destination Airport']")).click();
		
		
		WebElement tosearch = Driver.findElement(By.xpath("//div[@id='where-to']"));
		tosearch.sendKeys("BGI");
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
		
		Thread.sleep(2000l);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		 
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		Driver.findElement(By.xpath("//div[@class='when loaded']")).click();
		
		Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		
		WebElement date1 = Driver.findElement(By.xpath("//a[@data-di-id='di-id-a60d5599-4e83a1d1']"));
		date1.click();
		
		WebElement search = Driver.findElement(By.xpath("//a[@data-klass-id='foSubmitButton']"));
		search.click();
		
		Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Driver.findElement(By.xpath("//td[@class='UI__oneWayflightDetail '] ")).click();
		
		WebDriverWait wait = new WebDriverWait(Driver, 5);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"flightIndividual__component\"]/div/section/div/div/div/div[2]/div/div[3]/a/button")));
		element.click();
		
		Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement Seatselect = Driver.findElement(By.xpath("//*[@id=\"seats__component\"]/div/div/div/section/section/div[2]/div/div[2]/div[2]/label/span[1]"));
		Seatselect.click();
		
		Thread.sleep(3000l);
		
		WebElement luggage1 = Driver.findElement(By.xpath("//span[text()='25kg +£45.00'][1]"));
		luggage1.click();
		
		Thread.sleep(2000l);

		WebElement lugagge2 = Driver.findElement(By.xpath("//span[text()='20kg +£35.00'][1]"));
		lugagge2.click();
		
		
		WebDriverWait wait1 = new WebDriverWait(Driver, 3);
		WebElement element1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='CONTINUE']")));
		element1.click();
		
		WebElement Donation = Driver.findElement(By.xpath("//span[@class='inputs__box']"));
		Donation.click();
		
		WebDriverWait wait2 = new WebDriverWait(Driver, 3);
		WebElement element2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='CONTINUE']")));
		element2.click();
		
		Driver.manage().timeouts().implicitlyWait(17, TimeUnit.SECONDS);

		Driver.findElement(By.xpath("//a[text()='continue as a guest']")).click();
		
		Driver.findElement(By.xpath("//option[text()='Mr'][1]")).click();
		
		Driver.findElement(By.xpath("//input[@name='paxInfoFormBean[0].firstName']")).sendKeys(getData(0, 0));
		Driver.findElement(By.xpath("//input[@name='paxInfoFormBean[0].lastName']")).sendKeys(getData(0, 1));
		Driver.findElement(By.xpath("//input[@placeholder='DD']")).sendKeys("16");
		Driver.findElement(By.xpath("//input[@placeholder='MM']")).sendKeys("11");
		
		Thread.sleep(2000l);
		Driver.findElement(By.xpath("//input[@placeholder='YYYY']")).sendKeys("1993");
		Driver.findElement(By.xpath("//label[text()='Address']")).click();
		
		Driver.findElement(By.xpath("//input[@placeholder='House Number / Building Name']")).sendKeys("6");
		Driver.findElement(By.xpath("//input[@placeholder='Street Name']")).sendKeys(getData(0, 6));
		Driver.findElement(By.xpath("//input[@placeholder='City / Town']")).sendKeys(getData(0, 7));
		Driver.findElement(By.xpath("//input[@placeholder='Postcode']")).sendKeys(getData(0, 8));
		Driver.findElement(By.xpath("//label[text()='Mobile Phone']")).click();
		Driver.findElement(By.xpath("//input[@name='telephoneNum']")).sendKeys("9566121849");
		Driver.findElement(By.xpath("//input[@placeholder='Email (e.g name@email.com)']")).sendKeys("praasanth@icloud.com");
		Driver.findElement(By.xpath("//span[text()='ADULT 2']")).click();		
		Driver.findElement(By.xpath("//*[@id=\"pax-form\"]/div[2]/div[2]/div/div[1]/div/div/div/select/option[2]")).click();
		
		Driver.findElement(By.xpath("//input[@name='paxInfoFormBean[1].firstName']")).sendKeys(getData(1, 0));
		Driver.findElement(By.xpath("//input[@name='paxInfoFormBean[1].lastName']")).sendKeys(getData(1, 1));
		
		Thread.sleep(2000l);
		Driver.findElement(By.xpath("//span[text()='Flight Summary']")).click();
		
		
		Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		Driver.findElement(By.xpath("//*[@id=\"importantInformation__component\"]/div/div[3]/div/div[1]/label/span[1]")).click();
		
		WebDriverWait wait3 = new WebDriverWait(Driver, 3);
		WebElement element3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='CONTINUE']")));
		element3.click();
		
		TakesScreenshot tks = (TakesScreenshot) Driver;
		File temp = tks.getScreenshotAs(OutputType.FILE);
		
		File desc = new File("C:\\Users\\Prasanth\\eclipse-workspace\\TuiAirlines\\Screenshot\\Scrn.png");
		FileUtils.copyFile(temp, desc);
		
		
		


		

		


		
		

		
		
		
		
		
		
		
		
	
	}

}

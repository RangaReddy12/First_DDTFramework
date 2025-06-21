package utilities;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeTest
public static void setUp()throws Throwable
{
	conpro = new Properties();
	//load property file
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		try {
			throw new IllegalArgumentException("Browser Value is not matching");
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}
}
@AfterTest
public static void tearDown()
{
	driver.quit();
}
}

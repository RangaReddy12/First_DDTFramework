package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import utilities.AppUtil;

public class FunctionLibrary extends AppUtil{
//method for login 
	public static boolean adminLogin(String user,String pass) throws Throwable
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		Thread.sleep(2000);
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login Success   "+Expected+"     "+Actual,true);
			//click logout link
			Thread.sleep(1000);
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else
		{
			//capture error message
			String Error_Mess = driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
			Thread.sleep(2000);
			//clickok button
			driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
			Reporter.log(Error_Mess+"    "+Expected+"     "+Actual,true);
			return false; 
		
		}
		
	}
}

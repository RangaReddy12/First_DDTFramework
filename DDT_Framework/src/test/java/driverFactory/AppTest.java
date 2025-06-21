package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	String Fileinput ="./DataTables/LoginData.xlsx";
	String FileOutput ="./DataTables/DataDrivenResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		//define path of HTML into target folder
		reports = new ExtentReports("./target/ExtentReports/Login.Html");
	//create object for ExcelFileutil class
		ExcelFileUtil xl = new ExcelFileUtil(Fileinput);
		//count no of rows in Login sheet
		int rc = xl.rowCount("login");
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger=reports.startTest("Login Test");
			logger.assignAuthor("Ranga");
			//read username and password cell from excel
			String username =xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i, 1);
			logger.log(LogStatus.INFO, username+"--------------------"+password);
			//call adminlogin method from Functionlibaray class
			boolean res =FunctionLibrary.adminLogin(username, password);
			if(res)
			{
				//write as pass into status cell
				xl.setCelldata("Login", i, 2, "Pass", FileOutput);
				logger.log(LogStatus.PASS, "Valid Credentails");
			}
			else
			{
				//take screen shot
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./target/Screenshot/"+i+"Loginpage.png"));
				//write as Fail into status cell
				xl.setCelldata("Login", i, 2, "Fail", FileOutput);
				logger.log(LogStatus.FAIL, "InValid Credentails");
			}
			reports.endTest(logger);
			reports.flush();
		}
		
	}

}









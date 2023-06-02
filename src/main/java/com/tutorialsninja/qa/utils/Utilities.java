package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static WebDriver driver;
	public static ExtentReports extentReport;

	public static String genEmailDateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "kuttyKurama" + timeStamp + "@gmail.com";
	}

	// Getting prop file
	public static String getPropValue(String key) {
		String value = null;
		Properties p = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			p.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value = p.getProperty(key);
	}

	// Getting testdata from prop file
	public String getTestdata(String key) {
		Properties p = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
			p.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return p.getProperty(key);

	}

	// Initializing browser
	public void initializeBrowser(String browserName) {
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}

	// Getting Test Data from excel
	public static Object[][] getTestData(String sheetName) {

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\test\\resources\\com\\tutorialsninja\\qa\\testdata\\tutorialsninjaTestdata.xlsx");
		XSSFWorkbook workBook = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			workBook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workBook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][col];

		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < col; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}

			}
		}
		return data;

	}

	// Launch URL
	public void launchUrl(String url) {
		driver.get(url);
	}

	public static void generateExtendReport() {
		extentReport = new ExtentReports();

		File extentReportFile = new File(System.getProperty("user.dir") + "\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		extentReport.setSystemInfo("Application URL", getPropValue("url"));
		extentReport.setSystemInfo("Browser Name", getPropValue("browser"));
		extentReport.setSystemInfo("Email", getPropValue("validEmail"));
		extentReport.setSystemInfo("Password", getPropValue("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	// Taking Screenshot
	public static String takeScreenshot(String testName) throws IOException {
		File destination;
		String desPath = "";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		destination = new File(System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png");
		FileHandler.copy(source, destination);
		desPath = destination.getPath();

		return desPath;

	}

	// Close browser
	public void close() {
		driver.quit();
	}

}

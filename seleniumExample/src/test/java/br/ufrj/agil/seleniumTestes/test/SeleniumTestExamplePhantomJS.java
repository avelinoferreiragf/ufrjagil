package br.ufrj.agil.seleniumTestes.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumTestExamplePhantomJS  {

	private final String BROWSER_FILE_PATH = new File("drivers/phantomjs").getAbsolutePath();
	private final String DESTINY_FILE_PATH = new File("drivers/scs.png").getAbsolutePath();

	private WebDriver driver = null;
	private File destinyFile = new File(DESTINY_FILE_PATH);

	@Before
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("takesScreenshot", true);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, BROWSER_FILE_PATH);
		this.driver = new PhantomJSDriver(capabilities);
	}
	
	@Test
	public void buscarNoGoogleAcademico() throws Exception {
		this.driver.get("http://scholar.google.com.br/");

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, this.destinyFile);

		WebElement pesquisaElement = this.driver.findElement(By.id("gs_hp_tsi"));
		WebElement buttonElement = this.driver.findElement(By.tagName("button"));
	
		pesquisaElement.sendKeys("DCC UFRJ");
		buttonElement.click();
	
		WebElement body = this.driver.findElement(By.tagName("body"));
		String texto = body.getText();

		assertThat(texto, containsString("DCC"));
	}



	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	}
}

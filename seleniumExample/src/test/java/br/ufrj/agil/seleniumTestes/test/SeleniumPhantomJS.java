package br.ufrj.agil.seleniumTestes.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumPhantomJS  {

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
		this.destinyFile.delete();
	}
	
	@Test
	public void abrirTelaBrowser() throws Exception {
		this.driver.get("http://www.horariodebrasilia.org/");
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, this.destinyFile);

		assertThat(this.destinyFile.exists(), equalTo(true));
	}

	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	// Comente a linha abaixo para ver a imagem gerada.
		this.destinyFile.delete();

	}
}

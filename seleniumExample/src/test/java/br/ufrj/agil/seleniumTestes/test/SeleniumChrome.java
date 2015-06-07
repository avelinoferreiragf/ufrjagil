package br.ufrj.agil.seleniumTestes.test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumChrome  {

	private final Long SLEEP_TIME = 5000L;
	private final String BROWSER_FILE_PATH = new File("drivers/chromedriver").getAbsolutePath();
	private WebDriver driver = null;

	@Before
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver", BROWSER_FILE_PATH);
		 this.driver = new ChromeDriver();
	}
	
	@Test
	public void abrirTelaBrowser() throws Exception {
		driver.get("http://www.ufrj.br");
		Thread.sleep(SLEEP_TIME);
		
	}

	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	}
}

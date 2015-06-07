package br.ufrj.agil.seleniumTestes.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestExample  {

	private final String BROWSER_FILE_PATH = new File("drivers/chromedriver").getAbsolutePath();
	private WebDriver driver = null;

	@Before
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver", BROWSER_FILE_PATH);
		 this.driver = new ChromeDriver();
	}
	
	@Test
	public void falharLogin() throws Exception {
		this.abrirSiteDaUFRJ();
		this.abrirTelaDaIntranetDaUFRJ();
	
		WebElement botaoAcesso = this.driver.findElement(By.id("edit-submit"));
		botaoAcesso.click();
		WebElement loginElement = this.driver.findElement(By.id("username"));
		WebElement passwordElement = this.driver.findElement(By.id("password"));
		WebElement buttonElement = this.driver.findElement(By.tagName("button"));
	
		loginElement.sendKeys("MeuSuperCPF");
		passwordElement.sendKeys("12345");
		buttonElement.click();
	
		WebElement divErro = this.driver.findElement(By.id("flash_error"));
		String textoErro = divErro.getText();

		MatcherAssert.assertThat(textoErro, Matchers.equalTo("Incorrect username or password."));
	}

	private void abrirSiteDaUFRJ() {
		this.driver.get("http://www.ufrj.br");
	}

	private void abrirTelaDaIntranetDaUFRJ() {
		WebElement mapMenu = this.driver.findElement(By.id("Map"));
		WebElement areaIntranet = mapMenu.findElements(By.tagName("area")).get(1);
		areaIntranet.click();
		List<String> janelasAbertas = new ArrayList<String>(this.driver.getWindowHandles());
		driver.switchTo().window(janelasAbertas.get(1));
	}
	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	}
}

package br.ufrj.agil.seleniumTestes.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class VerRankingConcurseirosBrasilTest {

	private final String BROWSER_FILE_PATH = new File("drivers/phantomjs").getAbsolutePath();

	private WebDriver driver = null;

	@Before
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, BROWSER_FILE_PATH);
		this.driver = new PhantomJSDriver(capabilities);
	}
	
	@Test
	public void verRanking() throws Exception {
		this.driver.get("http://concurseirobrasil.com.br/#/2015/marinha/fuzileiros");
		
		WebElement botaoVerRanking = this.driver.findElement(By.linkText("Ver Ranking"));
		botaoVerRanking.click();
		
		WebElement titulo = this.driver.findElement(By.id("divDisp"));
		String conteudoTitulo = titulo.getText();

		assertThat(conteudoTitulo, containsString("Classificação - Concurso"));
	}

	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	}

}

package br.ufrj.agil.test.presentation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Selenium utilizando o driver do Google Chrome.
 * 
 * Faz a abertura simples da tela do browser.
 *
 */
public class SeleniumChrome  {

	private final String BROWSER_FILE_PATH = new File("drivers/chromedriver").getAbsolutePath();
	private WebDriver driver = null;
	private static final Integer TIMEOUT_AFTER_POST = 10;

	@Before
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver", BROWSER_FILE_PATH);
		 this.driver = new ChromeDriver();
	}
	
	@Test
	public void cadastrarUsuarioInformandoTodosOsDadosValidos() throws Exception {
		// Arrange
		String msgEsperada = StringEscapeUtils.unescapeHtml4("Usuu&#225;rio cadastrado com sucesso.");
		this.driver.get("http://localhost:8080/UFRJAgil");
		WebElement nomeElement = this.driver.findElement(By.id("nome"));
		WebElement enderecoElement = this.driver.findElement(By.id("endereco"));
		WebElement dataDeNascimentoElement = this.driver.findElement(By.id("dataDeNascimento"));
		WebElement btnCadastrar = this.driver.findElement(By.id("btnCadastrar"));
	
	
		// Act
		nomeElement.sendKeys("JOSE DAS COUVES FILHO");
		enderecoElement.sendKeys("RUA DAS PASSARGAS, No 123");
		dataDeNascimentoElement.sendKeys("08/06/1980");

		btnCadastrar.click();
		driver.manage().timeouts().pageLoadTimeout(TIMEOUT_AFTER_POST, TimeUnit.SECONDS);
		WebElement msg = this.driver.findElement(By.id("msg"));
		String msgRetornada = msg.getText();
	
		// Assert
		assertThat(msgRetornada, equalTo(msgEsperada));
	}

	@Test
	public void naoCadastrarUsuarioSemInformarNome() throws Exception {
		// Arrange
		String msgEsperada = StringEscapeUtils.unescapeHtml4("Nome do usu&#225;rio n&#227;o foi informado.");
		this.driver.get("http://localhost:8080/UFRJAgil");
		WebElement nomeElement = this.driver.findElement(By.id("nome"));
		WebElement enderecoElement = this.driver.findElement(By.id("endereco"));
		WebElement dataDeNascimentoElement = this.driver.findElement(By.id("dataDeNascimento"));
		WebElement btnCadastrar = this.driver.findElement(By.id("btnCadastrar"));
	
	
		// Act
		nomeElement.sendKeys("");
		enderecoElement.sendKeys("RUA DAS PASSARGAS, No 123");
		dataDeNascimentoElement.sendKeys("08/06/1980");

		btnCadastrar.click();
		driver.manage().timeouts().pageLoadTimeout(TIMEOUT_AFTER_POST, TimeUnit.SECONDS);
		WebElement msg = this.driver.findElement(By.id("msg"));
		String msgRetornada = msg.getText();
	
		// Assert
		assertThat(msgRetornada, equalTo(msgEsperada));
	}

	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	}
}

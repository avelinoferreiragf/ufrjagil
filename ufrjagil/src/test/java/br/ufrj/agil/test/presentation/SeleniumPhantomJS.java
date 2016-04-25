package br.ufrj.agil.test.presentation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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

/**
 * Selenium utilizando o driver do Selenium js (Headless browser).
 * 
 * Faz a abertura simples da tela do browser e tira um Screenshot.
 *
 */
public class SeleniumPhantomJS {

	private static final Integer TIMEOUT_AFTER_POST = 10;
	private final String BROWSER_FILE_PATH = new File("drivers/phantomjs")
			.getAbsolutePath();
	private final String DESTINY_DIR_PATH = new File("screenshots")
			.getAbsolutePath();

	private WebDriver driver = null;

	@Before
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("takesScreenshot", true);
		capabilities.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				BROWSER_FILE_PATH);
		this.driver = new PhantomJSDriver(capabilities);
	}

	private void takeScreenShot() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotFileName = this.getScreeshotFileName(this.getUltimoSequencial(this.ordenarArquivos(this.buscarArquivosDeHoje(DESTINY_DIR_PATH))));
		File destinyFile = new File(DESTINY_DIR_PATH + "/" + screenshotFileName);
		FileUtils.copyFile(srcFile, destinyFile);
		//System.out.println(this.DESTINY_FILE_PATH);
	}

	@Test
	public void cadastrarUsuarioInformandoTodosOsDadosValidos()
			throws Exception {
		// Arrange
		String msgEsperada = StringEscapeUtils
				.unescapeHtml4("Usuu&#225;rio cadastrado com sucesso.");
		this.driver.get("http://localhost:8080/UFRJAgil");
		WebElement nomeElement = this.driver.findElement(By.id("nome"));
		WebElement enderecoElement = this.driver.findElement(By.id("endereco"));
		WebElement dataDeNascimentoElement = this.driver.findElement(By
				.id("dataDeNascimento"));
		WebElement btnCadastrar = this.driver
				.findElement(By.id("btnCadastrar"));

		// Act
		nomeElement.sendKeys("JOSE DAS COUVES FILHO");
		enderecoElement.sendKeys("RUA DAS PASSARGAS, No 123");
		dataDeNascimentoElement.sendKeys("08/06/1980");

		this.takeScreenShot();
		btnCadastrar.click();
		driver.manage().timeouts()
				.pageLoadTimeout(TIMEOUT_AFTER_POST, TimeUnit.SECONDS);
		WebElement msg = this.driver.findElement(By.id("msg"));
		String msgRetornada = msg.getText();

		// Assert
		assertThat(msgRetornada, equalTo(msgEsperada));
		this.takeScreenShot();
	}

	@Test
	public void naoCadastrarUsuarioSemInformarNome() throws Exception {
		// Arrange
		String msgEsperada = StringEscapeUtils.unescapeHtml4("Nome do usu&#225;rio n&#227;o foi informado.");
		this.driver.get("http://localhost:8080/UFRJAgil");
		WebElement nomeElement = this.driver.findElement(By.id("nome"));
		WebElement enderecoElement = this.driver.findElement(By.id("endereco"));
		WebElement dataDeNascimentoElement = this.driver.findElement(By
				.id("dataDeNascimento"));
		WebElement btnCadastrar = this.driver
				.findElement(By.id("btnCadastrar"));

		// Act
		nomeElement.sendKeys("");
		enderecoElement.sendKeys("RUA DAS PASSARGAS, No 123");
		dataDeNascimentoElement.sendKeys("08/06/1980");

		this.takeScreenShot();
		btnCadastrar.click();
		driver.manage().timeouts()
				.pageLoadTimeout(TIMEOUT_AFTER_POST, TimeUnit.SECONDS);
		WebElement msg = this.driver.findElement(By.id("msg"));
		String msgRetornada = msg.getText();

		// Assert
		this.takeScreenShot();
		assertThat(msgRetornada, equalTo(msgEsperada));
	}


	@After
	public void setDown() {
		this.driver.close();
		this.driver.quit();
	}

	private File[] buscarArquivosDeHoje(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.startsWith(LocalDate.now().toString());
			}
		});
	}
	private File[] ordenarArquivos(File[] arquivos) {
		if (arquivos == null) {
			return null;
		}
		if (arquivos.length <= 1) {
			return arquivos;
		}
		Arrays.sort(arquivos, new Comparator<File>(){
			public int compare(File f1, File f2) {
				return f1.getName().compareTo(f2.getName());
			}
		});
		return arquivos;
	}

	private Integer getUltimoSequencial(File[] arquivos) {
		if (arquivos == null || arquivos.length == 0) {
			return 1;
		}
		String nomeArquivo = arquivos[arquivos.length - 1].getName();
		String strNumero = nomeArquivo.substring(nomeArquivo.lastIndexOf("-") + 1, nomeArquivo.indexOf(".png"));
		Integer numero = Integer.valueOf(strNumero);
		numero++;
		return numero;
	}

	private String getScreeshotFileName(Integer numero) {
		return LocalDate.now().toString() +"-"+ String.format("%03d", numero) +".png";
	}

}

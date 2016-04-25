package br.ufrj.agil.test.bddPresentation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ufrj.agil.util.LocalDateUtil;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class UsuarioStepsWithSelenium {

	Boolean usuarioPodeSerCadastrado = null;

	private final String BROWSER_FILE_PATH = new File("drivers/chromedriver").getAbsolutePath();
	private WebDriver driver = null;
	private static final Integer TIMEOUT_AFTER_POST = 10;

	public UsuarioStepsWithSelenium() {
		System.setProperty("webdriver.chrome.driver", BROWSER_FILE_PATH);
		this.driver = new ChromeDriver();
	}

	@Dado("^que o usuario criou um novo cadastro$")
	public void usuarioCriouUmNovoCadastro() {
		this.driver.get("http://localhost:8080/UFRJAgil");
	}
	
	@Dado("^que o usuario nao informou o nome")
	public void usuarioNaoInformouONome() {
		WebElement nomeElement = this.driver.findElement(By.id("nome"));
		nomeElement.sendKeys("");
	}
	
	@Dado("^que o usuario informou o nome \"([^\"]*)\"$")
	public void usuarioInformouUmNome(String nome) {
		WebElement nomeElement = this.driver.findElement(By.id("nome"));
		nomeElement.sendKeys(nome);
	}

	@Dado("^que o usuario informou o endereco \"([^\"]*)\"$")
	public void usuarioInformouUmEndereco(String endereco) {
		WebElement enderecoElement = this.driver.findElement(By.id("endereco"));
		enderecoElement.sendKeys(endereco);
	}

	@Dado("^que o usuario informou a data de nascimento \"([^\"]*)\"$")
	public void usuarioInformouUmaDataDeNascimento(String dataNascimento) {
		WebElement dataDeNascimentoElement = this.driver.findElement(By.id("dataDeNascimento"));
		dataDeNascimentoElement.sendKeys(dataNascimento);
	}

	@Dado("^que o usuario informou a data de nascimento menor do que 18 anos")
	public void informarDataMenorDe18Anos() {
		WebElement dataDeNascimentoElement = this.driver.findElement(By.id("dataDeNascimento"));
		LocalDate localDate = LocalDate.now();
		localDate.minusYears(17L);
		dataDeNascimentoElement.sendKeys(LocalDateUtil.convert(localDate));
	}

	@Quando("verificar se o usuario pode ser cadastrado$") 
	public void verificarSeUsuarioPodeSerCadastrado(){
		WebElement btnCadastrar = this.driver.findElement(By.id("btnCadastrar"));
		btnCadastrar.click();
	}

	@Entao("^receberá a mensagem \"([^\"]*)\"$")
	public void receberBooleano(String mensagemEsperada){
		driver.manage().timeouts().pageLoadTimeout(TIMEOUT_AFTER_POST, TimeUnit.SECONDS);
		WebElement msg = this.driver.findElement(By.id("msg"));
		String msgRetornada = msg.getText();
		assertThat(msgRetornada, equalTo(StringEscapeUtils.unescapeHtml4(mensagemEsperada)));
	}

	@After
	public void fechar() {
		this.driver.close();
		this.driver.quit();
	}
}

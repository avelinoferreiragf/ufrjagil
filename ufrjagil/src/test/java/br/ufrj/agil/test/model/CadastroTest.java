package br.ufrj.agil.test.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import br.ufrj.agil.model.Usuario;

public class CadastroTest {

	@Test
	public void naoPodeCadastrarSeTiverMenosDoQue18Anos() {
		// Arrange
		Boolean valorEsperado = false;
		LocalDate dataDeNascimento = LocalDate.of(2012, Month.DECEMBER, 27);
		Usuario usuario = new Usuario();
		usuario.setDataDeNascimento(dataDeNascimento);

		// Act
		Boolean ehMaiorDeIdade = usuario.temIdadeMinimaParaCadastro();

		// Assert
		assertThat(ehMaiorDeIdade, equalTo(valorEsperado));
	}

	@Test
	public void podeCadastrarSeTiverMaisDoQue18Anos() {
		// Arrange
		Boolean valorEsperado = true;
		LocalDate dataDeNascimento = LocalDate.of(1982, Month.MAY, 9);
		Usuario usuario = new Usuario();
		usuario.setDataDeNascimento(dataDeNascimento);

		// Act
		Boolean ehMaiorDeIdade = usuario.temIdadeMinimaParaCadastro();

		// Assert
		assertThat(ehMaiorDeIdade, equalTo(valorEsperado));
	}
}

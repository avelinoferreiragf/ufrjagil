package br.ufrj.agil.test.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import br.ufrj.agil.model.Usuario;

public class CadastroMockTest {

	@Test
	public void naoPodeCadastrarSeFizer18AnosAposODiaCorrente() {
		// Arrange
		Boolean valorEsperado = false;
		LocalDate dataDeNascimento = LocalDate.of(1998, Month.MARCH, 21);
		Usuario usuario = spy(new Usuario());
		usuario.setDataDeNascimento(dataDeNascimento);
		when(usuario.getHoje()).thenReturn(LocalDate.of(2016, Month.MARCH, 20));

		// Act
		Boolean ehMaiorDeIdade = usuario.temIdadeMinimaParaCadastro();

		// Assert
		assertThat(ehMaiorDeIdade, equalTo(valorEsperado));
	}

	@Test
	public void podeCadastrarSeFizer18AnosNoDiaCorrente() {
		// Arrange
		Boolean valorEsperado = true;
		LocalDate dataDeNascimento = LocalDate.of(1998, Month.MARCH, 20);
		Usuario usuario = spy(new Usuario());
		usuario.setDataDeNascimento(dataDeNascimento);
		when(usuario.getHoje()).thenReturn(LocalDate.of(2016, Month.MARCH, 20));

		// Act
		Boolean ehMaiorDeIdade = usuario.temIdadeMinimaParaCadastro();

		// Assert
		assertThat(ehMaiorDeIdade, equalTo(valorEsperado));
	}

}

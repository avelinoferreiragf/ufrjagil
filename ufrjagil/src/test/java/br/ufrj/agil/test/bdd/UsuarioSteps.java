package br.ufrj.agil.test.bdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import br.ufrj.agil.model.Usuario;
import br.ufrj.agil.util.LocalDateUtil;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class UsuarioSteps {

	private Usuario usuario = null;
	Boolean usuarioPodeSerCadastrado = null;

	@Dado("^que o usuario criou um novo cadastro$")
	public void usuarioCriouUmNovoCadastro() {
		usuario = new Usuario();
	}
	
	@Dado("^que o usuario nao informou o nome")
	public void usuarioNaoInformouONome() {
		usuario.setNome("");
	}
	
	@Dado("^que o usuario informou o nome \"([^\"]*)\"$")
	public void usuarioInformouUmNome(String nome) {
		usuario.setNome(nome);
	}

	@Dado("^que o usuario informou o endereco \"([^\"]*)\"$")
	public void usuarioInformouUmEndereco(String endereco) {
		usuario.setEndereco(endereco);
	}

	@Dado("^que o usuario informou a data de nascimento \"([^\"]*)\"$")
	public void usuarioInformouUmaDataDeNascimento(String dataNascimento) {
		usuario.setDataDeNascimento(LocalDateUtil.convert(dataNascimento));
	}

	@Quando("verificar se o usuario pode ser cadastrado$") 
	public void verificarSeUsuarioPodeSerCadastrado(){
		this.usuarioPodeSerCadastrado = this.usuario.podeSerCadastrado();
	}

	@Entao("^recebera booleano (true|false)$")
	public void receberBooleano(Boolean valorEsperado){
		assertThat(this.usuarioPodeSerCadastrado, equalTo(valorEsperado));
	}
}

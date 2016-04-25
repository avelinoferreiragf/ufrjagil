package br.ufrj.agil.business;

import br.ufrj.agil.model.Usuario;

public class CadastroUsuarioBusiness {

	public void cadastrar(Usuario usuario) {
		if (usuario.getNome().trim().equals("")) {
			throw new RuntimeException("Nome do usu&#225;rio n&#227;o foi informado.");
		}
		if (usuario.getEndereco().trim().equals("")) {
			throw new RuntimeException("Endere&#231;o usu&#225;rio n&#227;o foi informado.");
		}
		if (usuario.getDataDeNascimento() == null) {
			throw new RuntimeException("Data de nascimento do usu&#225;rio n&#227;o foi informada.");
		}
		if(!usuario.temIdadeMinimaParaCadastro()) {
			throw new RuntimeException("Usu&#225;rio n&#227;o tem idade m&#237;nima para cadastro.");
		}
	}
}

package br.ufrj.agil.service;

import br.ufrj.agil.business.CadastroUsuarioBusiness;
import br.ufrj.agil.infra.CadastroUsuarioDAO;
import br.ufrj.agil.model.Usuario;

public class CadastroUsuarioService {

	private CadastroUsuarioBusiness business = new CadastroUsuarioBusiness();
	private CadastroUsuarioDAO dao = new CadastroUsuarioDAO();

	public void cadastrar(Usuario usuario) {
		this.business.cadastrar(usuario);
		this.dao.cadastrar(usuario);
	}
}

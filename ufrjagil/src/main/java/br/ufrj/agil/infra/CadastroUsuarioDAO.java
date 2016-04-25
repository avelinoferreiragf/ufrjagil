package br.ufrj.agil.infra;

import java.util.HashSet;
import java.util.Set;

import br.ufrj.agil.model.Usuario;

public class CadastroUsuarioDAO {

	private static Long sequence = 1L;
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	public void cadastrar(Usuario usuario) {
		usuario.setId(sequence++);
		this.usuarios.add(usuario);
	}
}

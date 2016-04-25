package br.ufrj.agil.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable {

	/*
	 * Proprieades da classe.
	 */
	private static final long serialVersionUID = 9024252673075078353L;
	private static final Integer IDADE_MINIMA_PARA_CADASTRO = 18;
	/*
	 * Propriedades do objeto.
	 */
	private Long id;

	private String nome;

	private String endereco;

	private LocalDate dataDeNascimento;

	private LocalDate hoje = LocalDate.now();

	/*
	 * Métodos Get e Set
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public LocalDate getHoje() {
		return hoje;
	}

	public Boolean temIdadeMinimaParaCadastro() {
		LocalDate menorDataDeNascimentoPossivel  = this.getHoje().minusYears(IDADE_MINIMA_PARA_CADASTRO.longValue());
		return menorDataDeNascimentoPossivel.isAfter(this.getDataDeNascimento()) || menorDataDeNascimentoPossivel.isEqual(this.getDataDeNascimento());
	}
	/*
	 * Javanes
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario compare = (Usuario) obj;
		if (this.id == null || compare.id == null) {
			return false;
		}
		return this.id.equals(compare.id);
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = 17 * hash + (this.id == null ? 0 : this.id.hashCode());
		hash = 23 * hash + (this.nome == null ? 0 : this.nome.hashCode());
		hash = 31 * hash + (this.dataDeNascimento == null ? 0 : this.dataDeNascimento.hashCode());
		
		return hash;
	}

	public Boolean podeSerCadastrado() {
		if(this.nome == null || this.nome.trim().equals("")) {
			return false;
		}
		if(this.endereco == null || this.endereco.trim().equals("")) {
			return false;
		}
		if (this.dataDeNascimento == null) {
			return false;
		}
		return true;
	}
}

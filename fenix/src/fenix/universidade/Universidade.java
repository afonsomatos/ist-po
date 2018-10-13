package fenix.universidade;

import java.util.LinkedList;
import java.util.List;

import fenix.Pessoa;

public class Universidade {

	private String _nome;
	private List<Curso> _cursos = new LinkedList<Curso>();
	private List<Pessoa> _pessoas = new LinkedList<Pessoa>();
	
	Universidade(String nome) {
		_nome = nome;
	}
	
	public String getNome() {
		return _nome;
	}
	
	public void adicionarPessoa(Pessoa pessoa) {
		_pessoas.add(pessoa);
	}
	
	void adicionarCurso(Curso curso) {
		// TODO: Ver unicidade do nome
		_cursos.add(curso);
	}
	
}

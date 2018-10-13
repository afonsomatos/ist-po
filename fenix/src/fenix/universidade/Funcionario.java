package fenix.universidade;

import java.util.LinkedList;
import java.util.List;

import fenix.Pessoa;

public class Funcionario extends Pessoa {

	private List<Curso> _cursos = new LinkedList<Curso>();
	
	Funcionario(String nome, long telefone, Universidade universidade) {
		super(nome, telefone, universidade);
	}

	void adicionarCurso(Curso curso) {
		_cursos.add(curso);
	}
	
	@Override
	public String obterTipo() {
		return "FUNCIONÁRIO";
	}

}

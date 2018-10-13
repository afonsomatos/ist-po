package fenix.universidade;

import java.util.List;

public class Universidade {

	private String _nome;
	private List<Curso> _cursos;
	
	Universidade(String nome) {
		_nome = nome;
	}
	
	public String getNome() {
		return _nome;
	}
	
	void adicionarCurso(Curso curso) {
		// TODO: Ver unicidade do nome
		_cursos.add(curso);
	}
	
}

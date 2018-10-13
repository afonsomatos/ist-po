package fenix;

import java.util.List;

class Universidade {

	private String _nome;
	private List<Curso> _cursos;
	
	Universidade(String nome) {
		_nome = nome;
	}
	
	public String getNome() {
		return _nome;
	}
	
	void adicionarCurso(Curso curso) {
		_cursos.add(curso);
	}
	
}

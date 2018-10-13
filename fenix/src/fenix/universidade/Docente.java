package fenix.universidade;

import java.util.LinkedList;
import java.util.List;

import fenix.Pessoa;

class Docente extends Pessoa {

	private List<Curso> _cursos = new LinkedList<Curso>();
	private List<Disciplina> _disciplinas = new LinkedList<Disciplina>();
	
	Docente(String nome, long telefone, Universidade universidade) {
		super(nome, telefone, universidade);
	}
	
	void criarProjeto(String nome, String descricao, Disciplina disciplina) {
		if (_disciplinas.contains(disciplina)) {
			disciplina.adicionarProjeto(new Projeto(nome, descricao, disciplina));
		}
	}
	
	void fecharProjeto(Projeto projeto) {
		projeto.fechar();
	}
	
	void adicionarDisciplina(Disciplina disciplina) {
		_disciplinas.add(disciplina);
		disciplina.getCanal().registar(getCaixa());
	}
	
	void adicionarCurso(Curso curso) {
		_cursos.add(curso);
	}
	
	@Override
	public String obterTipo() {
		return "DOCENTE";
	}
	
	@Override
	public void mostrar() {
		super.mostrar();
		for (Disciplina d : _disciplinas)
			System.out.println("* " + d.getNome());
	}


}

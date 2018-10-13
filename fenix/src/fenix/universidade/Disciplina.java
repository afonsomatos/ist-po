package fenix.universidade;

import java.util.LinkedList;
import java.util.List;

import fenix.Aluno;
import fenix.notificacoes.Canal;

public class Disciplina {

	private String _nome;
	private int _capacidade;
	
	private Canal _canal = new Canal();
	
	private Curso _curso;
	private List<Aluno> _alunos 	= new LinkedList<Aluno>();
	private List<Docente> _docentes = new LinkedList<Docente>();
	private List<Projeto> _projetos = new LinkedList<Projeto>();

	Disciplina(String nome, int capacidade, Curso curso) {
		_nome = nome;
		_capacidade = capacidade;
		_curso = curso;
		
		curso.adicionarDisciplina(this);
	}
	
	public Canal getCanal() {
		return _canal;
	}
	
	public String getNome() {
		return _nome;
	}
	
	void adicionarDocente(Docente docente) {
		_docentes.add(docente);
		docente.adicionarDisciplina(this);
	}
	
	void adicionarAluno(Aluno aluno) {
		// TODO Verificar capacidade
		_alunos.add(aluno);
		// TODO Verificar se aluno pode se inscrever
		aluno.adicionarDisciplina(this);
	}
	
	public void adicionarProjeto(Projeto projeto) {
		_projetos.add(projeto);
	}
	
}

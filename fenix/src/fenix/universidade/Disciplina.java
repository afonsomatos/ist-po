package fenix.universidade;

import java.util.LinkedList;
import java.util.List;

import fenix.Aluno;

public class Disciplina {

	private String _nome;
	private int _capacidade;
	
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
	
	void adicionarDocente(Docente docente) {
		_docentes.add(docente);
	}
	
	void adicionarAluno(Aluno aluno) {
		// TODO Verificar capacidade
		_alunos.add(aluno);
	}
	
	void adicionarProjeto(Projeto projeto) {
		_projetos.add(projeto);
	}
	
}

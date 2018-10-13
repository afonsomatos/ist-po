package fenix;

import java.util.LinkedList;
import java.util.List;

public class Curso {

	private int _delegados = 0;
	private String _nome;
	
	private List<Aluno> 		_alunos			= new LinkedList<Aluno>();
	private List<Funcionario> 	_funcionarios	= new LinkedList<Funcionario>();
	private List<Docente> 		_docentes		= new LinkedList<Docente>();
	private List<Disciplina> 	_disciplinas	= new LinkedList<Disciplina>();

	private Universidade _universidade;
	
	Curso(String nome, Universidade universidade) {
		_nome = nome;
		_universidade = universidade;
	}
	
	void adicionarFuncionario(Funcionario funcionario) {
		_funcionarios.add(funcionario);
	}
	
	void adicionarAluno(Aluno aluno) {
		_alunos.add(aluno);
	}
	
	void adicionarDisciplina(Disciplina disciplina) {
		_disciplinas.add(disciplina);
	}
	
	void adicionarDocente(Docente docente) {
		_docentes.add(docente);
	}
	
}

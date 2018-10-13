package fenix;

import java.util.LinkedList;
import java.util.List;

import fenix.universidade.Curso;
import fenix.universidade.Disciplina;
import fenix.universidade.Projeto;

public class Aluno extends Pessoa {

	private Curso _curso;
	private boolean _delegado = false;
	
	private List<Disciplina> _disciplinas = new LinkedList<Disciplina>();
	
	public Aluno(String nome, long telefone, Curso curso) {
		super(nome, telefone, curso.getUniversidade());
		_curso = curso;
	}

	void submeterProjeto(Projeto projeto, String submissao) {
		projeto.adicionarSubmissao(this, submissao);
	}
	
	void serDelegado(boolean delegado) {
		// TODO Atualizar curso
		_delegado = delegado;
	}
	
	public void adicionarDisciplina(Disciplina disciplina) {
		if (_disciplinas.size() >= 6) return;
		
		_disciplinas.add(disciplina);
		disciplina.getCanal().registar(getCaixa());	
	}

	@Override
	public String obterTipo() {
		if (_delegado)
			return "DELEGADO";
		return "ALUNO";
	}
	
	@Override
	public void mostrar() {
		super.mostrar();
		for (Disciplina d : _disciplinas)
			System.out.println("* " + d.getNome());
	}

}

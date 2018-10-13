package fenix.universidade;

import java.util.HashMap;
import java.util.Map;

import fenix.Aluno;

public class Projeto {

	private boolean _aberto = true;
	private String _descricao;
	private String _nome;
	
	private Inquerito _inquerito = null;
	private Disciplina _disciplina;
	
	private Map<Aluno, String> submissoes = new HashMap<Aluno, String>();
	
	Projeto(String nome, String descricao, Disciplina disciplina) {
		_nome = nome;
		_descricao = descricao;
		_disciplina = disciplina;
		
		disciplina.adicionarProjeto(this);
	}
	
	public void fechar() {
		_aberto = false;
	}
	
	public void adicionarSubmissao(Aluno aluno, String submissao) {
		submissoes.put(aluno, submissao);
	}
	
	public String getNome() {
		return _nome;
	}
	
	Disciplina getDisciplina() {
		return _disciplina;
	}
	
	void alterarInquerito(Inquerito inquerito) {
		_inquerito = inquerito;
	}
	
}

package fenix.universidade;

public class Projeto {

	private boolean _aberto = true;
	
	private String _descricao;
	private String _nome;
	
	private Disciplina _disciplina;
	
	Projeto(String nome, String descricao, Disciplina disciplina) {
		_nome = nome;
		_descricao = descricao;
		_disciplina = disciplina;
		
		disciplina.adicionarProjeto(this);
	}
	
}

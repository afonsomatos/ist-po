package fenix;

import fenix.universidade.Curso;

public class Aluno extends Pessoa {

	private Curso _curso;
	private boolean _delegado = false;
	
	public Aluno(String nome, long telefone, Curso curso) {
		super(nome, telefone);
		_curso = curso;
	}

	void serDelegado(boolean delegado) {
		// TODO
		_delegado = delegado;
	}
	
	@Override
	public void mostrarPessoa() {
		
	}

}

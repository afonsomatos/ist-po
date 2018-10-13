package fenix;

public abstract class Pessoa {
	
	private static int _nextId = 100000;
	
	private String _nome;
	private long _telefone;
	private int _id;
	
	public Pessoa(String nome, long telefone) {
		_nome = nome;
		_telefone = telefone;
		_id = _nextId;
		_nextId++;
	}
	
	int getId() {
		return _id;
	}
	
	void alterarTelefone(long telefone) {
		_telefone = telefone;
	}
	
	long getTelefone() {
		return _telefone;
	}
	
	String getNome() {
		return _nome;
	}
	
	public abstract void mostrarPessoa();
	
}

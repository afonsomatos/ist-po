package fenix;

import java.util.LinkedList;
import java.util.List;

import fenix.notificacoes.Caixa;
import fenix.universidade.Universidade;

public abstract class Pessoa {
	
	private static int _nextId = 100000;
	
	private String _nome;
	private long _telefone;
	private int _id;

	private boolean _logado = false;
	
	private Caixa _caixa = new Caixa();
	
	private Universidade _universidade;
	
	public Pessoa(String nome, long telefone, Universidade universidade) {
		_nome = nome;
		_telefone = telefone;
		
		_id = _nextId;
		_nextId++;
		
		_universidade = universidade;
		_universidade.adicionarPessoa(this);
	}
	
	public Caixa getCaixa() {
		return _caixa;
	}
	
	void estarLogado(boolean logado) {
		_logado = logado;
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
	
	public abstract String obterTipo();
	
	public void mostrar() {
		String print = String.format("%s|%d|%s|%s", obterTipo(), _id, _telefone, _nome);
		System.out.println(print);
	};
	
}

package fenix.notificacoes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Caixa {
	
	private List<String> _mensagens	= new LinkedList<String>();
	private Set<Canal> _bloqueados 	= new HashSet<Canal>();
	
	public void bloquear(Canal canal) {
		_bloqueados.add(canal);
	}
	
	public void receber(Canal canal, String mensagem) {
		if (!_bloqueados.contains(canal))
			_mensagens.add(mensagem);
	}
	
	public List<String> obterMensagens() {
		return _mensagens;
	}
	
	public void limpar() {
		_mensagens.clear();
	}

}


package fenix.notificacoes;

import java.util.HashSet;
import java.util.Set;

public class Canal {
	
	public Set<Caixa> _caixas = new HashSet<Caixa>();
	
	public void registar(Caixa caixa) {
		_caixas.add(caixa);
	}
	
	public void emitir(String mensagem) {
		for (Caixa c : _caixas)
			c.receber(this, mensagem);
	}
	
}
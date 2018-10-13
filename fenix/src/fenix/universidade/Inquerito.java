package fenix.universidade;

import java.util.LinkedList;
import java.util.List;

public class Inquerito {

	private enum Estado {
		Criado,
		Aberto,
		Fechado,
		Finalizado
	};
	
	private List<Submissao> _submissoes = new LinkedList<Submissao>();
	private Estado _estado = Estado.Criado;
	private Projeto _projeto;
	
	Inquerito(Projeto projeto) {
		_projeto = projeto;
		
		projeto.
	}
	
	void abrir();
	void fechar();
	
	
}

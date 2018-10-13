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
		
		projeto.alterarInquerito(this);
	}
	
	void abrir() {
		_estado = Estado.Aberto;
		
		Disciplina disciplina = _projeto.getDisciplina();
		String mensagem = String.format("Pode preencher inquérito do projecto %s da disciplina %s", _projeto.getNome(), disciplina.getNome());
		disciplina.getCanal().emitir(mensagem);
	}
	
	void fechar() {
		_estado = Estado.Fechado;
		
		Disciplina disciplina = _projeto.getDisciplina();
		String mensagem = String.format("Resultados do inquérito do projecto %s da disciplina %s", _projeto.getNome(), disciplina.getNome());
		disciplina.getCanal().emitir(mensagem);
	}
	
	void finalizar() {
		_estado = Estado.Finalizado;
	}
	
	void cancelar() {
		_projeto.alterarInquerito(null);
	}
	
	void submeter(Submissao sub) {
		_submissoes.add(sub);
	}

	String obterResultados() {
		return "";
	}
	
	public class Submissao {
	
		private String _comentario;
		private int _horas;
		
		public Submissao(String comentario, int horas) {
			_comentario = comentario;
			_horas = horas;
		}
		
		public int getHoras() {
			return _horas;
		}
		
		public String getComentario() {
			return _comentario;
		}
		
	}
	
}

package fenix.universidade;

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
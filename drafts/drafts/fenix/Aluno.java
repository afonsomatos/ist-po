package projeto;

import java.util.HashMap;

class Aluno extends Pessoa {

    private Curso _curso;
    private Disciplina[] _disciplinas;
    private HashMap<Projecto, String> _submissoes = new HashMap<Projecto, String>();
    private boolean _delegado;

    Aluno(String nome, long telemovel) {
        super(nome, telemovel);
    }

    private void submeterProjecto(Projecto projecto, String submissao) {
        _submissoes.put(projecto, submissao);
    }

    boolean entregouProjecto(Projecto projecto) {
        return _submissoes.containsKey(projecto);
    }

    String obterSubmissao(Projecto projecto) {
        return _submissoes.get(projecto);
    }
}
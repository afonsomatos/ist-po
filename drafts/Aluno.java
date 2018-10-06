package projeto;

class Aluno extends Pessoa {

    private Curso _curso;
    private Disciplina[] _disciplinas;
    private HashMap<Projecto, String> submissoes = new HashMap<Projecto, String>();
    private boolean _delegado;

    void submeterProjecto(Projecto projecto, String submissao) {
        submissoes.put(projecto, submissao);
    }

}
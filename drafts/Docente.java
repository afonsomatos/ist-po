package projeto;

class Docente extends Pessoa {

    private Curso[] _cursos;
    private Disciplina[] _disciplinas;

    Docente(String nome, long telemovel) {
        super(nome, telemovel);
    }
    
}
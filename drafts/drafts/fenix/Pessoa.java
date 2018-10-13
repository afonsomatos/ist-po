package projeto;

class Pessoa {

    private static int _nextId = 10000;

    private String _nome;
    private long _telemovel;
    private int _id;

    Pessoa(String nome, long telemovel) {
        _nome = nome;
        _telemovel = _telemovel;
        _id = _nextId;
        _nextId++;
    }
    
}
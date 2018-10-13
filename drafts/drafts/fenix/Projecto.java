package projeto;

class Projecto {

    private boolean _aberto = true;
    private String _nome;
    private String _descricao;
    private Inquerito _inquerito = null;

    boolean aberto() {
        return _aberto;
    }
    
    void fechar() throws Exception {
        _aberto = false;
        if (_inquerito != null)
            _inquerito.abrir();
    }

}
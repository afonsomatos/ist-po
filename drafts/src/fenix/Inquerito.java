package projeto;

import java.util.LinkedList;

class Inquerito {

    private enum Estado {
        Criado,
        Aberto,
        Fechado,
        Finalizado;
    };

    private LinkedList<Aluno> _alunos = new LinkedList<Aluno>();
    private int _horasGastas;
    private LinkedList<String> _respostas = new LinkedList<String>();
    private Estado _estado = Estado.Criado;
    private Projecto _projecto;

    Inquerito(Projecto projecto) {
        _projecto = projecto;
    }

    void submeterResposta(Aluno aluno, String resposta) {
        if (aluno.entregouProjecto(_projecto) && !_alunos.contains(aluno)) {
            _respostas.add(resposta);
        }
    }

    void alterarEstado(Estado estado) {
        _estado = estado;
    }

    void cancelar() throws Exception {
        if (_estado == Estado.Fechado) {
            abrir();
        } else if (_estado == Estado.Finalizado) {
            throw new Exception("Impossivel cancelar inquerito finalizado.");
        } else if (_estado == Estado.Aberto && _respostas.size() >= 1) {
            throw new Exception("Impossivel cancelar inquerito com respostas.");
        } else {
            // Inquerito criado ou aberto sem respostas
            // TODO remover do sistema?
        }
    }
    
    void abrir() throws Exception {
        if (_estado == Estado.Criado && _projecto.aberto()) {
            throw new Exception("O projeto associado esta aberto.");
        }
        _estado = Estado.Aberto;
    }

    void fechar() throws Exception {
        if (_estado == Estado.Aberto) {
            _estado = Estado.Fechado;
        } else if (_estado != Estado.Fechado) {
            throw new Exception("O inquerito nao pode ser fechado");
        }
    }

    void finalizar() throws Exception {
        if (_estado == Estado.Fechado) {
            // impedir futuras alteracoes
            _estado = Estado.Finalizado;
        } else if (_estado != Estado.Finalizado) {
            throw new Exception("O inquerito nao pode ser finalizado");
        }
    }

    void obterResultados() { }

}
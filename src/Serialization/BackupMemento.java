package Serialization;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import engine.exceptions.PontoDeRestauroInexistenteException;
import engine.interfaces.Restauravel;
import java.util.Stack;

import utils.implementations.UtilsSerializacao;

/**
 * Template de um restauravel que implementa um padrão memento com serialização.
 * Qualquer subclasse terá apenas que fazer o override do método
 * restaurarEstado(Restauravel restauravel) para poder beneficiar da funcionalidade de
 * restauro. É de notar que o memento é guardado através da serialização.
 *
 * @author Ricardo José Horta Morais
 */
public class BackupMemento{

    
    /**
     * Stack de momentos é protegida da serialização mas pode ser usada numa
     * subclasse
     */
    protected Stack<Memento> stackMementos = new Stack();
    private final Restauravel restauravel;
    
    /**
     * Contrutor
     * @param restauravel Restauravel
     */
    public BackupMemento(Restauravel restauravel) {
        this.restauravel = restauravel;
    }

    /**
     * Restaura um ponto de restauro anterior caso exista
     *
     * @throws PontoDeRestauroInexistenteException caso não exista ponto de
     * restauro
     */
    public void restaurarPontoAnterior() throws PontoDeRestauroInexistenteException {

        if (stackMementos.isEmpty()) {
            throw new PontoDeRestauroInexistenteException();
        }

        Memento memento = stackMementos.pop();

        memento.restaurar();

    }

    /**
     * Cria um memento do estado atual
     */
    public void criarMemento() {

        Memento memento = new Memento(UtilsSerializacao.serializar(restauravel));
        stackMementos.push(memento);

    }

    /**
     * Restaura o estado a partir do jogo poderá ser feito override, este método
     * é chamado quando um memento é restaurado em caso subclasse é possível
     * fazer um cast para a subclass de Jogo e retirar os valor dos atributos.
     *
     * @param restauravel retauro do memento
     */
    protected void restaurarEstado(Restauravel restauravel){
        
        this.restauravel.restaurarEstado(restauravel);
        
    };


    /**
     * Representa um ponto de Restauro
     */
    protected class Memento {

        private byte[] dados;

        /**
         * Construtor
         *
         * @param dados dados serializados de um ponto de restauro
         */
        protected Memento(byte[] dados) {
            this.dados = dados;
        }

        /**
         * Restaura o memento pedindo ao jogo para alterar o seu estado a partir
         * dos dados deserializados
         */
        public void restaurar() {
            restaurarEstado(UtilsSerializacao.deserializar(Restauravel.class, dados));
        }

    }

}

package engine.interfaces;

/**
 * Representa um restauravel com de poder ser guardado o seu estado e restaurado
 * posteriormente
 *
 * @author Ricardo José Horta Morais
 */
public interface Restauravel {

    /**
     * Restaura o estado a partir de um restauravel poderá ser feito override,
     * este método é chamado quando um memento é restaurado em caso subclasse é
     * possível fazer um cast para a subclass do restauravel e retirar os valor
     * dos atributos.
     *
     * @param restauravel retauro do memento
     */
    public void restaurarEstado(Restauravel restauravel);

}


package engine.exceptions;

/**
 * Ocorre quando não existe um ponto de restauro
 * @author Ricardo José Horta Morais
 */
public class PontoDeRestauroInexistenteException extends Exception {
    
    /**
     * Construtor
     */
    public PontoDeRestauroInexistenteException() {
        super("Ponto de restauro não existe.");
    }

}

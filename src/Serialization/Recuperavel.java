/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.interfaces;

import engine.exceptions.PontoDeRestauroInexistenteException;

/**
 *
 * @author Morai
 */
public interface Recuperavel extends Restauravel{
    
    
    /**
     * Restaura um ponto de restauro anterior caso exista
     *
     * @throws PontoDeRestauroInexistenteException caso não exista ponto de
     * restauro
     */
    public void restaurarPontoAnterior() throws PontoDeRestauroInexistenteException;
    
    /**
     * Cria um ponto de restauro
     */
    public void criarPontoDeRestauro();
    
}

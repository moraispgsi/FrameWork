/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticStatements;

/**
 *
 * @author Morai
 */
public interface StaticValue {
    
    /**
     * Devolve o tipo do valor 
     * @return tipo do valor 
     */
    public StaticType getType();
    
    
    /**
     * Devolve o valor em formato String
     * @return 
     */
    public String getValue();
    
}

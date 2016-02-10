/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Morai
 * @param <T>
 */
public interface Output<T> {
    
    public String getName();

    public Class<T> getType();
    
    public ExecutingOutput<T> getExecutionInstance();

}

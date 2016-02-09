/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

/**
 *
 * @author Morai
 * @param <T>
 */
public interface Input<T> {
    
    public String getName();
    
    public Class<T> getType();
    
    public Output<T> getOutput();
    
    public void setOutput(Output<T> output);
    
    public ExecutingInput<T> getExecutingInput();
    
}

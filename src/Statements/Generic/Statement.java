/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.util.Set;

/**
 *
 * @author Morai
 */
public interface Statement {

    public String getName();

    public Set<Output> getOutputs();

    public Set<Input> getInputs();
    
    public ExecutingStatement getExecutingInstance();

}

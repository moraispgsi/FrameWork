/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Morai
 */
public interface Statement {
    
    public boolean missingDependency();
    
    public String generateJavaCode();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class RootStatement implements Statement{
    
    private Statement nextStatement;
    
    @Override
    public boolean missingDependency() {
        return nextStatement != null && nextStatement.missingDependency();
    }

    @Override
    public String generateJavaCode() {
        
        if(missingDependency())
            throw new RuntimeException("Missing Dependency.");
        
        if(nextStatement != null)
            nextStatement.generateJavaCode();
        
        return "";
    }

    public void setNextStatement(Statement nextStatement) {
        this.nextStatement = nextStatement;
    }
    
    
    
}

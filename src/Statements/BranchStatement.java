/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import DynamicClassUtils.DynamicClassUtils;

/**
 *
 * @author Morais
 */
public class BranchStatement implements Statement{

    private ReturningStatement conditionStatement;
    private Statement trueNextStatement;
    private Statement falseNextStatement;
    

    
    @Override
    public boolean missingDependency() {

        return ((conditionStatement == null || conditionStatement.missingDependency()) ||
                (trueNextStatement != null && trueNextStatement.missingDependency())   ||
                (falseNextStatement != null && falseNextStatement.missingDependency()) || 
                (trueNextStatement == null && falseNextStatement == null));
           
    }

    @Override
    public String generateJavaCode() {
        if(missingDependency())
            throw new RuntimeException("Dependency missing!");
        
       
        if(falseNextStatement == null){
            return "if("+conditionStatement.generateJavaCode()+"){\n\t" +
                    trueNextStatement.generateJavaCode() + "\n" +
                "}";
        }
        else if(trueNextStatement == null){
            return "if(!"+conditionStatement.generateJavaCode()+"){\n\t" +
                    falseNextStatement.generateJavaCode() + "\n" +
                "}";
        }else {
            
            return "if("+conditionStatement.generateJavaCode()+"){\n\t" +
                    trueNextStatement.generateJavaCode() + "\n" +
                "}else {\n\t"+
                    falseNextStatement.generateJavaCode() + "\n" +        
                "}";
            
        }

    }

    public void setConditionStatement(ReturningStatement conditionStatement) {
        if(!Boolean.class.isAssignableFrom(DynamicClassUtils.primitiveToWrapper(conditionStatement.getReturnType())))
            throw new RuntimeException("ReturningStatement type invalid!");
        this.conditionStatement = conditionStatement;
    }

    public void setTrueNextStatement(Statement trueNextStatement) {
        this.trueNextStatement = trueNextStatement;
    }

    public void setFalseNextStatement(Statement falseNextStatement) {
        this.falseNextStatement = falseNextStatement;
    }

    

}

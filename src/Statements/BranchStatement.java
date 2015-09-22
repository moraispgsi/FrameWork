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
                (trueNextStatement != null && trueNextStatement.missingDependency())||
                (falseNextStatement != null && falseNextStatement.missingDependency()));
           
    }

    @Override
    public String generateJavaCode() {
        if(missingDependency())
            throw new RuntimeException("Dependency missing!");
        
        if(trueNextStatement == null && falseNextStatement == null)
            return "";
        else if(trueNextStatement != null){
            return "if("+conditionStatement.generateJavaCode()+"){\n" +
                    trueNextStatement.generateJavaCode() + "\n" +
                "}";
        }
        else if(falseNextStatement == null){
            return "if(!"+conditionStatement.generateJavaCode()+"){\n" +
                    falseNextStatement.generateJavaCode() + "\n" +
                "}";
        }else {
            
            return "if("+conditionStatement.generateJavaCode()+"){\n" +
                    trueNextStatement.generateJavaCode() + "\n" +
                "}else {\n"+
                    falseNextStatement.generateJavaCode() + "\n" +        
                "}";
            
        }

    }

    public void setConditionStatement(ReturningStatement conditionStatement) {
        if(!Boolean.class.isAssignableFrom(DynamicClassUtils.primitiveToWrapper(conditionStatement.getReturnType())))
            throw new RuntimeException("ReturningStatement type invalid!");
        this.conditionStatement = conditionStatement;
    }

    public Statement getTrueNextStatement() {
        return trueNextStatement;
    }

    public Statement getFalseNextStatement() {
        return falseNextStatement;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import DynamicClassUtils.DynamicClassUtils;
import java.lang.reflect.Method;

/**
 *
 * @author Morai
 */
public class ReturnStatement implements Statement{
    
    private Method method;
    private ReturningStatement returningStatement;

    public ReturnStatement(Method method) {
        if(method.getReturnType() == Void.class)
            throw new RuntimeException("Void is not returnable.");
        
        this.method = method;
    }

    public void setReturningStatement(ReturningStatement returningStatement) {
        
        if(!DynamicClassUtils.primitiveToWrapper(method.getReturnType())
                .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(returningStatement.getReturnType())))
            throw new RuntimeException("ReturningStatement type invalid!");

        this.returningStatement = returningStatement;
        
    }

    @Override
    public boolean missingDependency() {
        return returningStatement == null || returningStatement.missingDependency();
    }

    @Override
    public String generateJavaCode() {
        return "return " + returningStatement.generateJavaCode() + ";";
    }
    
    
    
    
}

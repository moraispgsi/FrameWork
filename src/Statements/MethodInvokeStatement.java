/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import DynamicClassUtils.DynamicClassUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Morais
 */
public class MethodInvokeStatement implements ReturningStatement {
    
    private Method method;
    private ReturningStatement [] arguments = null;
    private Statement nextStatement;

    
    @Override
    public boolean missingDependency() {
        
        if(method == null || arguments == null|| method.getParameterCount() != arguments.length)
            return true;
        
        for(ReturningStatement arg : arguments){
            if(arg == null)
                return true;
        }
            
        Class<?> [] paramTypes = method.getParameterTypes();

        for(int i=0;i<method.getParameterCount();i++){
            
            if(!DynamicClassUtils.primitiveToWrapper(paramTypes[i]).isAssignableFrom(
                    DynamicClassUtils.primitiveToWrapper(arguments[i].getReturnType())))
                return true;
            
            if(arguments[i].missingDependency())
                return true;
            
        }
        
        return false;

    }

    @Override
    public String generateJavaCode() {
        
        if(missingDependency())
            throw new RuntimeException("Missing Dependency.");
        
        String result = method.getName() + "(";
        
        String aux = "";
        
        for(ReturningStatement arg : arguments){
            
            String gen = arg.generateJavaCode().trim();
            if(gen.endsWith("\n")){
                gen = gen.substring(0,gen.length()-1).trim();
            }
            
            if(gen.endsWith(";")){
                gen = gen.substring(0,gen.length()-1).trim();
            }
            aux += gen + ",";

        }
        
        result += aux.substring(0, aux.length()-1);
        
        result += ")";
        return result;
        
    }

    public void setMethod(Method method) {
        
        if(method.getReturnType().equals(Void.class))
            throw new RuntimeException("Must return something.");
        this.method = method;
        arguments = new ReturningStatement[method.getParameterCount()];
        
    }

    public Method getMethod() {
        return method;
    }
    
    public void setArgument(int i, ReturningStatement returningStatement){
        if(arguments == null)
            throw new RuntimeException("Consider inserting a method to invoke first.");
        
        arguments[i] = returningStatement;

    }

    public ReturningStatement [] getArguments() {
        
        return Arrays.copyOf( arguments, arguments.length);
        
    }

    @Override
    public Class<?> getReturnType() {
        if(method == null)
            throw new RuntimeException("Method not assigned.");
            
        return method.getReturnType();
    }

    public Statement getNextStatement() {
        return nextStatement;
    }

    public void setNextStatement(Statement nextStatement) {
        this.nextStatement = nextStatement;
    }


    
    
    
 
}

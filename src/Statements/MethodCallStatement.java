/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morais
 */
public class MethodCallStatement implements ReturningStatement {
    
    private Method method;
    private final List<ReturningStatement> arguments = new ArrayList<>();

    @Override
    public boolean missingDependency() {
        
        if(method == null || method.getParameterCount() != arguments.size())
            return true;
            
        Class<?> [] paramTypes = method.getParameterTypes();
        
        ReturningStatement [] statements = new ReturningStatement[arguments.size()];
        arguments.toArray(statements);
        
        
        for(int i=0;i<method.getParameterCount();i++){
            
            if(!DynamicClassUtils.DynamicClassUtils.primitiveToWrapper(paramTypes[i]).isAssignableFrom(
                    DynamicClassUtils.DynamicClassUtils.primitiveToWrapper(statements[i].getReturnType())))
                return true;
            
            if(statements[i].missingDependency())
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
        
        result += ");";
        return result;
        
    }

    public void setMethod(Method method) {
        
        if(method.getReturnType().equals(Void.class))
            throw new RuntimeException("Must return something.");
        this.method = method;
        
    }

    public Method getMethod() {
        return method;
    }

    public List<ReturningStatement> getArguments() {
        return arguments;
    }

    @Override
    public Class<?> getReturnType() {
        if(method == null)
            throw new RuntimeException("Method not assigned.");
            
        return method.getReturnType();
    }
    
    
 
}

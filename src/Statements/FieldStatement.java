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
public class FieldStatement implements ReturningStatement{

    private String name;
    private Class<?> type;
    
    @Override
    public Class<?> getReturnType() {
        return type;
    }

    @Override
    public boolean missingDependency() {
       return name == null || name.isEmpty() || type == null;
    }

    @Override
    public String generateJavaCode() {
        if(missingDependency())
            throw new RuntimeException("Missing Dependency");
        
        return name;
    }

    public void setName(String name) {
        //REGEX name valid
        this.name = name;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
    
}

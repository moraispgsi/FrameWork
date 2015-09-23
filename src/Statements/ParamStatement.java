/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Morais
 */
public class ParamStatement implements FieldReference{
    
    private final Class<?> type;
    private final String name;
    
    public ParamStatement(Class<?> type, String name) {
        this.type = type;
        this.name = name;
    }
    
  
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getType() {
        return type;
    }
    
    
}

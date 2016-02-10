/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import Statements.Generic.JavaFX.NewStageStatement;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Morai
 */
public abstract class StatementFactory {
    
    private final List<Class<? extends Statement>> listOfStatements;

    protected StatementFactory(List<Class<? extends Statement>> listOfStatements) {
        
        this.listOfStatements = new ArrayList(listOfStatements);
        
    }
  
    public Statement getStatement(String name){
        
        try {
            Optional<Class<? extends Statement>> st = listOfStatements.stream().filter((c) -> c.getSimpleName().equals(name)).findAny();
            if(st.isPresent())
                return (Statement) st.get().getDeclaredConstructors()[0].newInstance();
            
            
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException();
        }
        
        throw new RuntimeException();
        
    }
    
    public List<String> getStatementsAvailable(){
        return listOfStatements.stream().map((e) -> e.getSimpleName()).collect(Collectors.toList());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Morai
 */
public class StatementFactory {
    
    private static List<Class<? extends Statement>> listOfStatements = Arrays.asList(
            ConcatStringStatement.class,
            ConsolePrintStatement.class,
            FileOpenStatement.class,
            InputStreamToStringStatement.class,
            JoinedRunnablesStatement.class,
            NewThreadStatement.class,
            RunnableStatement.class,
            SimpleCalculatorStatement.class,
            NewStageStatement.class

    );

    public static Statement getStatement(String name){
        
        try {
            Optional<Class<? extends Statement>> st = listOfStatements.stream().filter((c) -> c.getSimpleName().equals(name)).findAny();
            if(st.isPresent())
                return (Statement) st.get().getDeclaredConstructors()[0].newInstance();
            
            
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException();
        }
        
        throw new RuntimeException();
        
    }
    
    
    
}

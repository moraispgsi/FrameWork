/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morai
 */
public class ConsolePrintStatement implements Statement {

    private BaseStatement base;
    private final String stringInput = "string";

    public ConsolePrintStatement() {
        base = new BaseStatement("print", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<EndOutput> constantValue1 = new BaseOutput<EndOutput>("runnable", EndOutput.class) {

            @Override
            public ExecutingOutput<EndOutput> getExecutionInstance() {
                
                try {
                    String resultado = (String) base.mapInputs().get(stringInput).getExecutingInput().getOutput().getValue();
                    
                    return () -> {
                        System.out.println(resultado);
                        return null;
                    };
                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(ConsolePrintStatement.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput(stringInput, String.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };
        

        SortedMap<String, Input> mapInputs = new TreeMap();
        mapInputs.put(input1.getName(), input1);

        return mapInputs;

    }

    @Override

    public Set<Output> getOutputs() {
        return base.getOutputs();
    }

    @Override
    public Set<Input> getInputs() {
        return base.getInputs();
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public ExecutingStatement getExecutingInstance() {
        return () -> {

        };
    }

}
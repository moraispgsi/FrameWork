/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic.GeneralPurpose;

import Statements.Output;
import Statements.ExecutingOutput;
import Statements.ExecutingInput;
import Statements.Input;
import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingStatement;
import Statements.Statement;
import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */
public class SimpleCalculatorStatement implements Statement {   

    private BaseStatement base;

    public SimpleCalculatorStatement() {
        base = new BaseStatement("Calculadora", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Double> sumOutput = new BaseOutput<Double>("soma", Double.class) {

            @Override
            public ExecutingOutput<Double> getExecutionInstance() {
                return () -> {
                    Double a = (Double) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    Double b = (Double) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    
                    return a + b;

                };
            }

        };
        
        Output<Double> divisionOutput = new BaseOutput<Double>("divisão", Double.class) {

            @Override
            public ExecutingOutput<Double> getExecutionInstance() {
                return () -> {
                    Double a = (Double) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    Double b = (Double) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    
                    return a / b;

                };
            }

        };
        
        Output<Double> subtractionOutput = new BaseOutput<Double>("subtração", Double.class) {

            @Override
            public ExecutingOutput<Double> getExecutionInstance() {
                return () -> {
                    Double a = (Double) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    Double b = (Double) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    
                    return a - b;

                };
            }

        };
        
        Output<Double> multiplicationOutput = new BaseOutput<Double>("multiplicação", Double.class) {

            @Override
            public ExecutingOutput<Double> getExecutionInstance() {
                return () -> {
                    Double a = (Double) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    Double b = (Double) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    
                    return a * b;

                };
            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(sumOutput.getName(), sumOutput);
        mapOutputs.put(divisionOutput.getName(), divisionOutput);
        mapOutputs.put(subtractionOutput.getName(), subtractionOutput);
        mapOutputs.put(multiplicationOutput.getName(), multiplicationOutput);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput("A", Double.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        Input input2 = new BaseInput("B", Double.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        SortedMap<String, Input> mapInputs = new TreeMap();
        mapInputs.put(input1.getName(), input1);
        mapInputs.put(input2.getName(), input2);
       
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

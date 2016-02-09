/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */
public class SimpleCalculatorStatement implements Statement {

    public enum Operator {

        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE;

        @Override
        public String toString() {

            switch (this) {
                case PLUS:
                    return "Addition";
                case MINUS:
                    return "Subtraction";
                case MULTIPLY:
                    return "Multiplication";
                case DIVIDE:
                    return "division";

            }
            return "Null";
        }
    }

    private BaseStatement base;

    public SimpleCalculatorStatement() {
        base = new BaseStatement("Calculadora", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Double> constantValue1 = new BaseOutput<Double>("resultado", Double.class) {

            @Override
            public ExecutingOutput<Double> getExecutionInstance() {
                return () -> {
                    Double a = (Double) base.mapInputs().get("A").getExecutingInput().getOutput().getValue();
                    Double b = (Double) base.mapInputs().get("B").getExecutingInput().getOutput().getValue();
                    Operator c = (Operator) base.mapInputs().get("Operador").getExecutingInput().getOutput().getValue();
                    switch (c) {
                        case DIVIDE:
                            return a / b;
                        case MINUS:
                            return a - b;
                        case MULTIPLY:
                            return a * b;
                        case PLUS:
                            return a + b;
                        default:
                            return a + b;
                    }

                };
            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
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

        Input input3 = new BaseInput("Operador", Operator.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        SortedMap<String, Input> mapInputs = new TreeMap();
        mapInputs.put(input1.getName(), input1);
        mapInputs.put(input3.getName(), input3);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Morai
 */
public class ExtensionStatement<T> implements Statement{
    
    private BaseStatement base;

    public ExtensionStatement(Class<T> type) {
        base = new BaseStatement("Extensão", generateOutputs(type), generateInputs(type));
    }

    private SortedMap<String, Output> generateOutputs(Class<T> type) {

        Output<T> constantValue1 = new BaseOutput<T>("Ponte", type) {

            @Override
            public ExecutingOutput<T> getExecutionInstance() {

                return () -> {

                    T a = (T) base.mapInputs().get("ponte").getExecutingInput().getOutput().getValue();
                   
                    return a;

                };
            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);

        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs(Class<T> type) {

        Input input1 = new BaseInput("ponte", type) {

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

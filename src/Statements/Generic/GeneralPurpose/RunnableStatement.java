/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic.GeneralPurpose;

import Statements.Output;
import Statements.ExecutingOutput;
import Statements.OutputNotAvailableException;
import Statements.ExecutingInput;
import Statements.EndOutput;
import Statements.Input;
import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingStatement;
import Statements.Statement;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morai
 */
public class RunnableStatement implements Statement {

    private BaseStatement base;
    private final String runnableInput = "EndOutput";

    public RunnableStatement() {
        base = new BaseStatement("Novo runnable", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Runnable> constantValue1 = new BaseOutput<Runnable>(runnableInput, Runnable.class) {

            @Override
            public ExecutingOutput<Runnable> getExecutionInstance() {

                Runnable runnable = () -> {
                    try {
                        base.mapInputs().get(runnableInput).getExecutingInput().getOutput().getValue();
                    } catch (OutputNotAvailableException ex) {
                        Logger.getLogger(RunnableStatement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
                return () -> {
                    return runnable;
                };

            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput(runnableInput, EndOutput.class) {

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morai
 */
public class NewThreadStatement implements Statement {

    private BaseStatement base;
    private final String runnableInput = "runnable";

    public NewThreadStatement() {
        base = new BaseStatement("Nova thread", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Thread> constantValue1 = new BaseOutput<Thread>("thread", Thread.class) {

            @Override
            public ExecutingOutput<Thread> getExecutionInstance() {

                try {

                    Runnable runnable = (Runnable) base.mapInputs().get(runnableInput).getExecutingInput().getOutput().getValue();
                    Thread thread = new Thread(runnable);
                    thread.start();

                    return () -> {
                        return thread;
                    };

                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(FileOpenStatement.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput(runnableInput, Runnable.class) {

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic.JavaFX;

import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingInput;
import Statements.ExecutingOutput;
import Statements.ExecutingStatement;
import Statements.Input;
import Statements.Output;
import Statements.OutputNotAvailableException;
import Statements.Statement;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

/**
 *
 * @author Morai
 */
public class CreateButtonStatement implements Statement {

    private BaseStatement base;
    private final String textInput = "text";
    private final String runnableInput = "runnable";

    public CreateButtonStatement() {
        base = new BaseStatement("Criar botão", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Button> constantValue1 = new BaseOutput<Button>("button", Button.class) {

            @Override
            public ExecutingOutput<Button> getExecutionInstance() {

                try {
                    String text = (String) base.mapInputs().get(textInput).getExecutingInput().getOutput().getValue();
                    Runnable runnable = (Runnable) base.mapInputs().get(runnableInput).getExecutingInput().getOutput().getValue();
                    Button button = new Button(text);
                    button.setOnAction(e->{
                        runnable.run();
                    });
                
                    return () -> {
                        return button;
                    };
                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(NewStageStatement.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput(textInput, String.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };
        
        Input input2 = new BaseInput(runnableInput, Runnable.class) {

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
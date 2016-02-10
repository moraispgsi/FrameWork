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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Morai
 */
public class CreateTextPaneStatement implements Statement {

    private BaseStatement base;
    private final String stringInput = "string";

    public CreateTextPaneStatement() {
        base = new BaseStatement("Criar pane com String", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Pane> constantValue1 = new BaseOutput<Pane>("pane", Pane.class) {

            @Override
            public ExecutingOutput<Pane> getExecutionInstance() {

                try {
                    String string = (String) base.mapInputs().get(stringInput).getExecutingInput().getOutput().getValue();

                    Label label = new Label(string);

                    StackPane pane = new StackPane(label);

                    return () -> {
                        return pane;
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

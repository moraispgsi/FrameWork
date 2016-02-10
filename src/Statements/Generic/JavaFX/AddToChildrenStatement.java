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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Morai
 */
public class AddToChildrenStatement implements Statement {

    private BaseStatement base;
    private final String paneInput = "pane";
    private final String nodeInput = "node";

    public AddToChildrenStatement() {
        base = new BaseStatement("Adicionar à pane", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Pane> constantValue1 = new BaseOutput<Pane>("pane", Pane.class) {

            @Override
            public ExecutingOutput<Pane> getExecutionInstance() {

                try {
                    Pane parent = (Pane) base.mapInputs().get(paneInput).getExecutingInput().getOutput().getValue();
                    Node node = (Node) base.mapInputs().get(nodeInput).getExecutingInput().getOutput().getValue();
                    parent.getChildren().add(node);
                    

                    return () -> {
                        return parent;
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

        Input input1 = new BaseInput(paneInput, Pane.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };
        
        Input input2 = new BaseInput(nodeInput, Node.class) {

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
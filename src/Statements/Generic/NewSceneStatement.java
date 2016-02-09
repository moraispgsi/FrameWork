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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Morai
 */
public class NewSceneStatement implements Statement {

    private BaseStatement base;
    private final String rootInput = "rootNode";
    private final String widthInput = "width";
    private final String heightInput = "height";

    public NewSceneStatement() {
        base = new BaseStatement("Nova scene", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<Scene> constantValue1 = new BaseOutput<Scene>("scene", Scene.class) {

            @Override
            public ExecutingOutput<Scene> getExecutionInstance() {
                
                try {
                    Parent root = (Parent) base.mapInputs().get(rootInput).getExecutingInput().getOutput().getValue();
                    Double width = (Double) base.mapInputs().get(widthInput).getExecutingInput().getOutput().getValue();
                    Double height= (Double) base.mapInputs().get(heightInput).getExecutingInput().getOutput().getValue();
                    
                    Scene scene = new Scene(root,width,height);
                    
                    return () -> {
                        return scene;
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

        Input input1 = new BaseInput(rootInput, Parent.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };
        
        
        Input input2 = new BaseInput(widthInput, Double.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        
        
        Input input3 = new BaseInput(heightInput, Double.class) {

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
          mapInputs.put(input3.getName(), input3);

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
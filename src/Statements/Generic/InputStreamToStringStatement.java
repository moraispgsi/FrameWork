/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morai
 */
public class InputStreamToStringStatement implements Statement {

    private BaseStatement base;

    public InputStreamToStringStatement() {
        base = new BaseStatement("InputStream -> String", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<String> constantValue1 = new BaseOutput<String>("conteúdo", String.class) {

            @Override
            public ExecutingOutput<String> getExecutionInstance() {

                InputStream stream = null;
                try {
                    stream = (InputStream) base.mapInputs().get("Input stream").getExecutingInput().getOutput().getValue();
                    Scanner sc = new Scanner(stream);
                    StringBuilder builder = new StringBuilder();
                    while(sc.hasNext()){
                        builder.append(sc.nextLine());
                        builder.append('\n');
                    }
                    
                    String resultado = builder.toString();
                    return () -> {
                        return resultado;
                    };
                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(InputStreamToStringStatement.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(InputStreamToStringStatement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;

            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();

        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput("Input stream", InputStream.class) {

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

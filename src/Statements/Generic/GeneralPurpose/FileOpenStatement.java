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
import Statements.Input;
import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingStatement;
import Statements.Statement;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
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
public class FileOpenStatement implements Statement {

    private BaseStatement base;

    public FileOpenStatement() {
        base = new BaseStatement("Abrir ficheiro", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<InputStream> constantValue1 = new BaseOutput<InputStream>("Const1", InputStream.class) {

            @Override
            public ExecutingOutput<InputStream> getExecutionInstance() {
                
                try {
                    
                    String nome = (String) base.mapInputs().get("Nome ficheiro").getExecutingInput().getOutput().getValue();
                    
                    Path file = FileSystems.getDefault().getPath(nome);
                    try{
                        InputStream in = Files.newInputStream(file);
                        return () -> {
                            return  in;
                        };
                    } catch (IOException x) {
                        System.err.println(x);
                    }
                    return null;
                    
                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(FileOpenStatement.class.getName()).log(Level.SEVERE, null,ex);
                }
                return null;

            }

        };
        
       
        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput("Nome ficheiro", String.class){

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class IOSocketSet implements IOSocketPluggable{
    
    private final ObservableSet<VariableInputSocket> inputSockets =  FXCollections.observableSet();
    private final ObservableSet<VariableOutputSocket> outputSockets = FXCollections.observableSet();
    private final ObservableSet<InputCallSocket> inputCallSockets =  FXCollections.observableSet();
    private final ObservableSet<OutputCallSocket> outputCallSockets = FXCollections.observableSet();

    @Override
    public ObservableSet<VariableInputSocket> getVariableInputSockets() {
        return inputSockets;
    }

    @Override
    public ObservableSet<VariableOutputSocket> getVariableOutputSockets() {
        return outputSockets;
    }
    
    @Override
    public ObservableSet<InputCallSocket> getInputCallSockets() {
        return inputCallSockets;
    }

    @Override
    public ObservableSet<OutputCallSocket> getOutputCallSockets() {
        return outputCallSockets;
    }
    
    
    
}

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
    private final ObservableSet<UIInputCallSocket> inputCallSockets =  FXCollections.observableSet();
    private final ObservableSet<UIOutputCallSocket> outputCallSockets = FXCollections.observableSet();

    @Override
    public ObservableSet<VariableInputSocket> getInputSockets() {
        return inputSockets;
    }

    @Override
    public ObservableSet<VariableOutputSocket> getOutputSockets() {
        return outputSockets;
    }
    
    public ObservableSet<UIInputCallSocket> getInputCallSockets() {
        return inputCallSockets;
    }

    public ObservableSet<UIOutputCallSocket> getOutputCallSockets() {
        return outputCallSockets;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketSets;


import CodeUIConnector.CallSockets.CallInput;
import CodeUIConnector.CallSockets.CallOutput;
import CodeUIConnector.ParamSockets.ParamInput;
import CodeUIConnector.ParamSockets.ParamOutput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class SocketSet implements Pluggable{
    
    private final ObservableSet<ParamInput> inputSockets =  FXCollections.observableSet();
    private final ObservableSet<ParamOutput> outputSockets = FXCollections.observableSet();
    private final ObservableSet<CallInput> inputCallSockets =  FXCollections.observableSet();
    private final ObservableSet<CallOutput> outputCallSockets = FXCollections.observableSet();

    @Override
    public ObservableSet<ParamInput> getInputParams() {
        return inputSockets;
    }

    @Override
    public ObservableSet<ParamOutput> getOutputParams() {
        return outputSockets;
    }
    
    @Override
    public ObservableSet<CallInput> getCallInputs() {
        return inputCallSockets;
    }

    @Override
    public ObservableSet<CallOutput> getCallOutputs() {
        return outputCallSockets;
    }
    
    
    
}

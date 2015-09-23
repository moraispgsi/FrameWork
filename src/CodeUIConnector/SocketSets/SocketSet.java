/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketSets;


import CodeUIConnector.InvokeSockets.Controller.InvokeInput;
import CodeUIConnector.InvokeSockets.Controller.InvokeOutput;
import CodeUIConnector.ParamSockets.Controller.ParamInput;
import CodeUIConnector.ParamSockets.Controller.ParamOutput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class SocketSet implements Pluggable{
    
    private final ObservableSet<ParamInput> inputSockets =  FXCollections.observableSet();
    private final ObservableSet<ParamOutput> outputSockets = FXCollections.observableSet();
    private final ObservableSet<InvokeInput> inputCallSockets =  FXCollections.observableSet();
    private final ObservableSet<InvokeOutput> outputCallSockets = FXCollections.observableSet();

    @Override
    public ObservableSet<ParamInput> getInputParams() {
        return inputSockets;
    }

    @Override
    public ObservableSet<ParamOutput> getOutputParams() {
        return outputSockets;
    }
    
    @Override
    public ObservableSet<InvokeInput> getCallInputs() {
        return inputCallSockets;
    }

    @Override
    public ObservableSet<InvokeOutput> getCallOutputs() {
        return outputCallSockets;
    }
    
    
    
}

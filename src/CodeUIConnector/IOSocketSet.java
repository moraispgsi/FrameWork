/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class IOSocketSet implements IOSocketPluggable{
    
    private final ObservableSet<UIVariableInputSocket> inputSockets =  FXCollections.observableSet();
    private final ObservableSet<UIVariableOutputSocket> outputSockets = FXCollections.observableSet();

    @Override
    public ObservableSet<UIVariableInputSocket> getInputSockets() {
        return inputSockets;
    }

    @Override
    public ObservableSet<UIVariableOutputSocket> getOutputSockets() {
        return outputSockets;
    }
    
}

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
import javafx.collections.ObservableSet;

public interface Pluggable {
    
    public ObservableSet<ParamInput> getInputParams();

    public ObservableSet<ParamOutput> getOutputParams();
    
    public ObservableSet<CallInput> getCallInputs();
    
    public ObservableSet<CallOutput> getCallOutputs();
    
}

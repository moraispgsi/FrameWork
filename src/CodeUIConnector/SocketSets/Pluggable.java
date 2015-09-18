/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketSets;

import CodeUIConnector.Connectors.CallInput;
import CodeUIConnector.Connectors.CallOutput;
import CodeUIConnector.Connectors.ParamInput;
import CodeUIConnector.Connectors.ParamOutput;
import javafx.collections.ObservableSet;

public interface Pluggable {
    
    public ObservableSet<ParamInput> getInputParams();

    public ObservableSet<ParamOutput> getOutputParams();
    
    public ObservableSet<CallInput> getCallInputs();
    
    public ObservableSet<CallOutput> getCallOutputs();
    
}

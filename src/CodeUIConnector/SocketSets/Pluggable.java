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
import javafx.collections.ObservableSet;

public interface Pluggable {
    
    public ObservableSet<ParamInput> getInputParams();

    public ObservableSet<ParamOutput> getOutputParams();
    
    public ObservableSet<InvokeInput> getCallInputs();
    
    public ObservableSet<InvokeOutput> getCallOutputs();
    
}

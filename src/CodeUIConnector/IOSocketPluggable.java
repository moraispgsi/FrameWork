/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.collections.ObservableSet;

public interface IOSocketPluggable {
    
    public ObservableSet<VariableInputSocket> getInputSockets();

    public ObservableSet<VariableOutputSocket> getOutputSockets();
    
    public ObservableSet<UIInputCallSocket> getInputCallSockets();
    
    public ObservableSet<UIOutputCallSocket> getOutputCallSockets();
    
}

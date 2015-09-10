/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.util.Set;
import javafx.collections.ObservableSet;

public interface IOSocketPluggable {
    
    public ObservableSet<UIVariableInputSocket> getInputSockets();

    public ObservableSet<UIVariableOutputSocket> getOutputSockets();
    
}

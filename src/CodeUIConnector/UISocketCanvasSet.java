/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.collections.ObservableSet;


public class UISocketCanvasSet implements IOSocketPluggable {

    private final IOSocketSet socketSet = new IOSocketSet();
    


    @Override
    public ObservableSet<VariableInputSocket> getInputSockets() {
        return socketSet.getInputSockets();
    }

    @Override
    public ObservableSet<VariableOutputSocket> getOutputSockets() {
        return socketSet.getOutputSockets();
    }
    
    @Override
    public ObservableSet<UIInputCallSocket> getInputCallSockets() {
        return socketSet.getInputCallSockets();
    }

    @Override
    public ObservableSet<UIOutputCallSocket> getOutputCallSockets() {
        return socketSet.getOutputCallSockets();
    }

}

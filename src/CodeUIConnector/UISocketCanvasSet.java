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
    public ObservableSet<UIVariableInputSocket> getInputSockets() {
        return socketSet.getInputSockets();
    }

    @Override
    public ObservableSet<UIVariableOutputSocket> getOutputSockets() {
        return socketSet.getOutputSockets();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class UIInputCallSocket {
    
    private final UISocket uiSocket;

    public UIInputCallSocket() {
        this.uiSocket = new UIVariableSocket(UIVariableSocket.Type.INPUT,5,"Executar");
    }

    public UISocket getUISocket() {
        return uiSocket;
    }
    
}

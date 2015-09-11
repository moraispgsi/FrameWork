/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class UIOutputCallSocket {
    
    private final UISocket uiSocket;
    private UIInputCallSocket connectSocket;

    public UIOutputCallSocket(String name) {

        this.uiSocket = new UIVariableSocket(UIVariableSocket.Type.OUTPUT,5,name);
    }

    public void setConnect(UIInputCallSocket connectSocket) {
        this.connectSocket = connectSocket;
    }

    public UIInputCallSocket getConnectSocket() {
        return connectSocket;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }

    
}
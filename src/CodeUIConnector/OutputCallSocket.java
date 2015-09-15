/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class OutputCallSocket {
    
    private final UISocket uiSocket;
    private InputCallSocket connectSocket;
    
    
    
    public OutputCallSocket(String name) {

        this.uiSocket = new UICallSocket(UICallSocket.Type.OUTPUT,5,name);
    }

    public void setConnect(InputCallSocket connectSocket) {
        this.connectSocket = connectSocket;
    }

    public InputCallSocket getConnectSocket() {
        return connectSocket;
    }

    public UISocket getUISocket() {
        return uiSocket;
    }

    
}
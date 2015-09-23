/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.InvokeSockets.Controller;

import CodeUIConnector.CallSockets.UI.UICallSocket;
import CodeUIConnector.SocketPane.UISocket;
import Statements.Statement;

/**
 * Represents a logical call input socket. 
 * @author Ricardo José Horta Morais
 */
public class InvokeInput {
    
    private final UISocket uiSocket;
    private final Statement statement;
            
    
    /**
     * Constructor
     * Builds the UISocket
     * @param statement statement
     */
    public InvokeInput(Statement statement) {
        this.uiSocket = new UICallSocket(UICallSocket.Type.INPUT,5,"Executar");
        this.statement = statement;
    }
    /**
     * Getter for the UISocket
     * @return UISocket UI representation of the CallInput
     */
    public UISocket getUISocket() {
        return uiSocket;
    }

    public Statement getStatement() {
        return statement;
    }
    
    
    
}

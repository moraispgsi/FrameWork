/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class UICondition extends UISocketPane{
    

    public UICondition() {
        super("Condition");
        
        OutputCallSocket callOutputSocket = new OutputCallSocket("Next");
        addOutputCallSocket(callOutputSocket);
        
        InputCallSocket callInputSocket = new InputCallSocket();
        addInputCallSocket(callInputSocket);
        
        VariableInputSocket socket = new VariableInputSocket(Boolean.class,"Condition");
        addInputSocket(socket);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


public class UICondition extends IOSocketPane{
    

    public UICondition() {
        super("Condition");
        
        VariableInputSocket socket = new VariableInputSocket(Boolean.class,"Condition");
        addInputSocket(socket);

    }

}

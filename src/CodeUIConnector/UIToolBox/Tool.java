/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.UIToolBox;

import CodeUIConnector.UIMethodFlowBoard;

/**
 *
 * @author Joana
 */
public interface Tool {
    
    public String getDisplayName();
    
    public void execute(UIMethodFlowBoard uiMethodFlowBoard);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.UIToolBox;


import CodeUIConnector.UIMethodFlowBoard;
import javafx.scene.control.ListView;

/**
 * The ToolBox provides access to diferent types o UISocketPane constructions.
 * @author Ricardo José Horta Morais
 */
public class ToolBox extends ListView{

    public ToolBox(UIMethodFlowBoard flowBoard) {
        
        
        getItems().add("Invoke Function");
        this.setOnMouseClicked(e->{
            
            if(e.getClickCount() == 2){
            
            if(this.getSelectionModel().getSelectedItems().get(0).equals("Invoke Function"))
                (new FunctionInvokeTool()).execute(flowBoard);
        
        
            }
            
        });
        
        
        getItems().add("Condition");
                
        getItems().add("Declare Field");
        
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.ParamSockets.Controller;

import CodeUIConnector.ParamSockets.UI.UIConstantValueChooser;
import CodeUIConnector.SocketPane.UISocket;
import Statements.ExecutingOutput;
import Statements.Output;
import java.util.Arrays;
import java.util.Set;


public class ParamConstantOutput<T> implements ParamOutput {

    private final Output output;
    private final UISocket uiSocket;


    public ParamConstantOutput(Class<T> type,Set<T> options) {
        
        
        UIConstantValueChooser uiParam = new UIConstantValueChooser(
                            UIConstantValueChooser.Type.OUTPUT,
                            5,
                            options);
        this.output = new Output(){

            @Override
            public String getName() {
                return "Constant";
            }

            @Override
            public Class getClassType() {
                return type;
            }

            @Override
            public ExecutingOutput getExecutionInstance() {
                T value = (T) uiParam.getValueBox().getSelectionModel().getSelectedItem();
                
                return ()->{
                   return value;
                };
            }
            
        };
        
        
        this.uiSocket = uiParam;
        
    }

    @Override
    public Output getOutput() {
        return output;
    }

    @Override
    public Class<?> getVariableType() {
        return output.getClassType();
    }

    @Override
    public String getName() {
        return output.getName();
    }

    @Override
    public UISocket getUISocket() {
        return uiSocket;
    }


    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UIMethod extends IOSocketPane implements IOSocketPluggable{
    
    private final IOSocketSet socketSet = new IOSocketSet();

    
    public Method method;
    public String classFileUrl;

    public UIMethod(String classFileUrl,Method method) {
        super(method.getName());
        
        this.method = method;

        this.classFileUrl = classFileUrl;

        for(Parameter param : method.getParameters()){

            UIVariableInputSocket socket = new UIVariableInputSocket(param.getType(),addInput(param.getName()+":"+param.getType().getSimpleName()));
            socketSet.getInputSockets().add(socket);
            
        }
     
        
        Class<?> returnType = method.getReturnType();
        
        if(!method.getReturnType().equals(Void.TYPE)){
            
            UIVariableOutputSocket socket = new UIVariableOutputSocket(returnType,addOutput(returnType.getSimpleName()));
            
            socketSet.getOutputSockets().add(socket);

            
        }

    }
    
    @Override
    public ObservableSet<UIVariableInputSocket> getInputSockets() {
        return socketSet.getInputSockets();
    }

    @Override
    public ObservableSet<UIVariableOutputSocket> getOutputSockets() {
        return socketSet.getOutputSockets();
    }

}

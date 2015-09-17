/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;



import DynamicClassUtils.DynamicClassUtils;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableSet;



public class UISocketCanvasSet implements IOSocketPluggable {

    private final IOSocketSet socketSet = new IOSocketSet();

    public UISocketCanvasSet() {
        
        

    }
    
    public void findAvailableInputSockets(Class<?> type){
        socketSet
                .getVariableInputSockets()
                .stream()
                .filter(socket -> 
                        DynamicClassUtils.primitiveToWrapper(socket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(type)))
                .forEach(socket -> {socket.getUISocket().showAvailable();});

    }
    
    public void findAvailableOutputSockets(Class<?> type){
        
        socketSet
                .getVariableOutputSockets()
                .stream()
                .filter(socket -> 
                        DynamicClassUtils.primitiveToWrapper(socket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(type)))
                .forEach(socket -> {socket.getUISocket().showAvailable();});
        
    }
    
    public void makeInputSocketUnavailable(){
        
        socketSet
                .getVariableInputSockets()
                .stream()
                .filter(socket -> socket.getUISocket().getCurrentShowType() == UISocket.ShowType.AVAILABLE)
                .forEach(socket -> {
                    socket.getUISocket().showConnected();
                });
        
    }
    
    public void makeOutputSocketUnavailable(VariableOutputSocket outputSocket){
        
        if(outputSocket.getUISocket().getCurrentShowType() != UISocket.ShowType.AVAILABLE)
            return;
        
        for(VariableInputSocket socket : socketSet.getVariableInputSockets()){
            if(socket.getOutputSource() != null &&
                    outputSocket == socket.getOutputSource()){
                outputSocket.getUISocket().showConnected();
                return;
            }
        }
        
        outputSocket.getUISocket().showIdle();

    }
    
    public void makeOutputSocketUnavailable(){
        
        Set<VariableOutputSocket> exceptions = new HashSet<>();
        
        socketSet
                .getVariableInputSockets()
                .stream()
                .filter(socket -> socket.getOutputSource() != null &&
                        socket.getOutputSource().getUISocket().getCurrentShowType() == UISocket.ShowType.AVAILABLE)
                .forEach(socket -> {
                    exceptions.add(socket.getOutputSource());
                    socket.getOutputSource().getUISocket().showConnected();
                });
        
        socketSet
                .getVariableOutputSockets()
                .stream()
                .filter(socket -> (!exceptions.contains(socket)))
                .forEach(socket -> socket.getUISocket().showIdle());

    }

    @Override
    public ObservableSet<VariableInputSocket> getVariableInputSockets() {
        return socketSet.getVariableInputSockets();
    }

    @Override
    public ObservableSet<VariableOutputSocket> getVariableOutputSockets() {
        return socketSet.getVariableOutputSockets();
    }
    
    @Override
    public ObservableSet<InputCallSocket> getInputCallSockets() {
        return socketSet.getInputCallSockets();
    }

    @Override
    public ObservableSet<OutputCallSocket> getOutputCallSockets() {
        return socketSet.getOutputCallSockets();
    }

}

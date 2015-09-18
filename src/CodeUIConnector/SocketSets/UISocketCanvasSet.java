/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketSets;

import CodeUIConnector.Connectors.CallInput;
import CodeUIConnector.Connectors.CallOutput;
import CodeUIConnector.Connectors.ParamInput;
import CodeUIConnector.Connectors.ParamOutput;
import CodeUIConnector.SocketPane.UISocket;
import DynamicClassUtils.DynamicClassUtils;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableSet;

public class UISocketCanvasSet implements IOSocketPluggable {

    private final IOSocketSet socketSet = new IOSocketSet();

    public UISocketCanvasSet() {

    }

    public void findAvailableInputSockets(Class<?> type) {
        socketSet
                .getInputParams()
                .stream()
                .filter(socket
                        -> DynamicClassUtils.primitiveToWrapper(socket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(type)))
                .forEach(socket -> {
                    socket.getUISocket().showAvailable();
                });

    }

    public void findAvailableOutputSockets(Class<?> type) {

        socketSet
                .getOutputParams()
                .stream()
                .filter(socket
                        -> DynamicClassUtils.primitiveToWrapper(socket.getVariableType())
                        .isAssignableFrom(DynamicClassUtils.primitiveToWrapper(type)))
                .forEach(socket -> {
                    socket.getUISocket().showAvailable();
                });

    }

    public void makeInputSocketUnavailable() {

        socketSet
                .getInputParams()
                .stream()
                .filter(socket -> socket.getUISocket().getCurrentlyShowing() == UISocket.ShowType.AVAILABLE)
                .forEach(socket -> {
                    socket.getUISocket().showConnected();
                });

    }

    public void makeOutputSocketUnavailable(ParamOutput outputSocket) {

        if (outputSocket.getUISocket().getCurrentlyShowing() != UISocket.ShowType.AVAILABLE) {
            return;
        }

        for (ParamInput socket : socketSet.getInputParams()) {
            if (socket.getOutputSource() != null
                    && outputSocket == socket.getOutputSource()) {
                outputSocket.getUISocket().showConnected();
                return;
            }
        }

        outputSocket.getUISocket().showIdle();

    }

    public void makeOutputSocketUnavailable() {

        Set<ParamOutput> exceptions = new HashSet<>();

        socketSet
                .getInputParams()
                .stream()
                .filter(socket -> socket.getOutputSource() != null
                        && socket.getOutputSource().getUISocket().getCurrentlyShowing() == UISocket.ShowType.AVAILABLE)
                .forEach(socket -> {
                    exceptions.add(socket.getOutputSource());
                    socket.getOutputSource().getUISocket().showConnected();
                });

        socketSet
                .getOutputParams()
                .stream()
                .filter(socket -> (!exceptions.contains(socket)))
                .forEach(socket -> socket.getUISocket().showIdle());

    }

    @Override
    public ObservableSet<ParamInput> getInputParams() {
        return socketSet.getInputParams();
    }

    @Override
    public ObservableSet<ParamOutput> getOutputParams() {
        return socketSet.getOutputParams();
    }

    @Override
    public ObservableSet<CallInput> getCallInputs() {
        return socketSet.getCallInputs();
    }

    @Override
    public ObservableSet<CallOutput> getCallOutputs() {
        return socketSet.getCallOutputs();
    }

}

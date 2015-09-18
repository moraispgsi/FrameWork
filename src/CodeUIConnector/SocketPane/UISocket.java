/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector.SocketPane;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.layout.Region;

public abstract class UISocket extends Region {

    private Handler onConnected;
    private Handler onAvailable;
    private Handler onIdle;

    public enum ShowType {

        CONNECTED, AVAILABLE, IDLE
    };

    private ShowType showing = ShowType.IDLE;

    public interface Handler {

        void handle();
    }

    public abstract ObjectProperty<Bounds> getPlugBoundsProperty();

    public void showConnected() {
        if (onConnected != null) {
            onConnected.handle();
        }

        showing = ShowType.CONNECTED;
    }

    public void showAvailable() {

        if (onAvailable != null) {
            onAvailable.handle();
        }

        showing = ShowType.AVAILABLE;

    }

    public void showIdle() {

        if (onIdle != null) {
            onIdle.handle();
        }

        showing = ShowType.IDLE;

    }

    public ShowType getCurrentlyShowing() {

        return showing;

    }

    public void setOnShowConnected(UISocket.Handler onConnected) {
        this.onConnected = onConnected;
    }

    public void setOnShowAvailable(UISocket.Handler onAvailable) {
        this.onAvailable = onAvailable;
    }

    public void setOnShowIdle(UISocket.Handler onIdle) {
        this.onIdle = onIdle;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.layout.Region;

public abstract class UISocket extends Region{
    
    private Handler onConnect;
    private Handler onAvailable;
    private Handler onIdle;
    
    public enum ShowType{CONNECTED,AVAILABLE,IDLE};
    
    private ShowType showType = ShowType.IDLE;
    
    public interface Handler {
        void handle();
    } 
    
    public abstract ObjectProperty<Bounds> getPlugBoundsProperty();
    

    public void showConnected(){
        if(onConnect != null)
            onConnect.handle();
        
        showType = ShowType.CONNECTED;
    }
    
    public void showAvailable(){
        
        if(onAvailable != null)
            onAvailable.handle();
        
        showType = ShowType.AVAILABLE;
        
    }
    
    public void showIdle(){

        if(onIdle != null)
            onIdle.handle();
        
        showType = ShowType.IDLE;
        
    }
    
    public ShowType getCurrentShowType(){
        
        return showType;
        
    }


    public void setOnShowConnect(UISocket.Handler onConnect) {
        this.onConnect = onConnect;
    }

    public void setOnShowAvailable(UISocket.Handler onAvailable) {
        this.onAvailable = onAvailable;
    }

    public void setOnShowIdle(UISocket.Handler onIdle) {
        this.onIdle = onIdle;
    }
    
    
    
}

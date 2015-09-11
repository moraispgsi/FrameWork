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

    public abstract ObjectProperty<Bounds> getPlugBoundsProperty();

    public abstract void setAvailable();

    public abstract void setConnected();

    public abstract void setIdle();
    
}

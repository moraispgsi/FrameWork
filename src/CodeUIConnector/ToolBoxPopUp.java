/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Morai
 */
public class ToolBoxPopUp extends PopupControl {
    
    public ToolBoxPopUp() {
    
        StackPane pane = new StackPane();
        pane.getChildren().addAll(new Button("Teste"));

        getScene().setRoot(pane);
    }
    
}

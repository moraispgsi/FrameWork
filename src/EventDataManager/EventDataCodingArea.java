/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TabPane;



public class EventDataCodingArea extends TabPane {

    private final Map<String,EventDataTab> pathToTab = new HashMap<>();
    
    public EventDataCodingArea() {
        
        
    }
    
    public void openTab(String filePath){
        
        if(pathToTab.containsKey(filePath)){
            getSelectionModel().select(pathToTab.get(filePath));
            return;
        }

        EventDataTab tab = new EventDataTab(filePath);

        getTabs().add(tab);

        pathToTab.put(filePath,tab);
        getSelectionModel().select(tab);

        tab.setOnCloseRequest(e->{

            pathToTab.remove(filePath);

        });

    }

}

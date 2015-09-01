/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;



public class FileContextMenu extends ContextMenu{
    
    
   private FileActionHandler openHandle;
   private FileActionHandler debugHandle; 
   private FileActionHandler deleteHandle;
   
   
   private MenuItem openItem;
   private MenuItem debugItem;
   private MenuItem deleteItem;
   
   
   private final String filePath;
    
    public FileContextMenu(String filePath) {
        
        this.filePath = filePath; 
        
        buildOpenFile();
        buildDebugFile();
        buildDeleteItem();
        
        
    }
    
    private void buildOpenFile(){
        
        openItem = new MenuItem("Open");
        
        openItem.setOnAction(e->{
            
            if(openHandle != null)
                openHandle.handle(filePath);
        });
        
        getItems().add(openItem);
        
    }
    
    private void buildDebugFile(){
    
        debugItem = new MenuItem("Debug");
        
        debugItem.setOnAction(e->{
            
            if(debugHandle != null)
                debugHandle.handle(filePath);
        });
        
        getItems().add(debugItem);
    
    }
    
    
    private void buildDeleteItem(){
        
        deleteItem = new MenuItem("Delete");
        
        deleteItem.setOnAction(e->{
            
            File file = new File(filePath);
            if(!file.exists() || !file.delete())
                return;//Can't delete
                
            
            
            if(deleteHandle != null)
                deleteHandle.handle(filePath);
            
        });
        
        getItems().add(deleteItem);

    }


    public void setOpenHandle(FileActionHandler openHandle) {
        this.openHandle = openHandle;
    }

    public void setDebugHandle(FileActionHandler debugHandle) {
        this.debugHandle = debugHandle;
    }
    
    public void setDeleteHandle(FileActionHandler deleteHandle) {
        this.deleteHandle = deleteHandle;
    }
    
    

}

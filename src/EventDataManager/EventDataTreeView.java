/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class EventDataTreeView extends TreeView {

    private final File projectIconFile = new File("src/Main/Project.png");
    private final Image projectIconImage = new Image(projectIconFile.toURI().toString());
    
    private final File packageIconFile = new File("src/Main/Package.png");
    private final Image packageIconImage = new Image(packageIconFile.toURI().toString());
    
    private final File eventDataIconFile = new File("src/Main/EventData.png");
    private final Image eventDataIconImage = new Image(eventDataIconFile.toURI().toString());
    
    private String projectPath;
    
    
    private TreeItem<Label> rootItem;
    private final Map<TreeItem<Label>,String> filesMap = new HashMap<>();
    private final Map<TreeItem<Label>,String> dirMap = new HashMap<>();
    
    private FileActionHandler onFileOpenRequest;
    
    private final IntegerProperty iconSize = new SimpleIntegerProperty(15);
    
    
    public EventDataTreeView() {
        
        ImageView rootIcon = createIcon(projectIconImage);
        rootItem = new TreeItem<>(new Label("Project"),rootIcon);
        
        setRoot(rootItem);
        
        setOnMouseClicked(e->{
            
            if(e.getClickCount() == 2){
                TreeItem<Label> item = (TreeItem<Label>) getSelectionModel().getSelectedItem();
 
                if(onFileOpenRequest!= null && filesMap.containsKey(item))
                    onFileOpenRequest.handle(filesMap.get(item));
                
            }

        });

    }

    public void setOnFileOpenRequest(FileActionHandler onFileOpenRequest) {
        this.onFileOpenRequest = onFileOpenRequest;
    }

    public void setProjectPath(String projectPath) {
        
        if(this.projectPath != null && this.projectPath.equals(projectPath))
            return;
        
        this.projectPath = projectPath;
        update();

    }
    
    public void update(){
        
        filesMap.clear();
        
        
        Map<String,Boolean> expandedDirectories = new HashMap<>();
        
        for(TreeItem dir : rootItem.getChildren()){
            
            if(dirMap.containsKey(dir)){
                expandedDirectories.put(dirMap.get(dir),dir.isExpanded());
            }
            
        }

        dirMap.clear();


        rootItem.getChildren().clear();
        
        File projectDir = new File(projectPath);
        
        for(File dir :projectDir.listFiles()){
            
            if(!dir.isDirectory()){
                continue;
            }
            
            
            
            TreeItem<Label> packageItem = makePackageItem(dir);
            
            if(expandedDirectories.containsKey(dir.getAbsolutePath())){
                packageItem.setExpanded(expandedDirectories.get(dir.getAbsolutePath()));  
            }
            
            dirMap.put(packageItem, dir.getAbsolutePath());
            

            rootItem.getChildren().add(packageItem);
            
            FilenameFilter filter = (File directory, String fileName) -> fileName.endsWith(".java");

            for(File file : dir.listFiles(filter)){

                if(!file.exists() || 
                        file.isDirectory()){
                    
                    continue;
                }
                
                try
                {
                    String fileContent = new Scanner(file).useDelimiter("\\Z").next();

                    Pattern findExtension = Pattern.compile("class[\\s\\S]*?\\sextends\\s*(\\w*)");

                    Matcher match = findExtension.matcher(fileContent);

                    if(!match.find())
                        continue;

                    if(!match.group(1).equals("EventData"))
                        continue;
                    
                    
                    //TODO: Package and file name test.


                } catch (FileNotFoundException ex) {
                    continue;
                }

                TreeItem<Label> fileItem = makeFileItem(file,packageItem);

                packageItem.getChildren().add(fileItem);

                filesMap.put(fileItem, file.getAbsolutePath());

            }
 
            

        }


    }
    
    
    private TreeItem<Label> makeFileItem(File file,TreeItem<Label> dirItem){
        
        if(file == null || file.isDirectory()){
            throw new RuntimeException("Invalid file.");
        }
        
        FileContextMenu fileContextMenu = new FileContextMenu(file.getAbsolutePath());
        
 
        Label nameLabel = new Label(file.getName());
        nameLabel.setContextMenu(fileContextMenu);

        TreeItem<Label> fileItem = new TreeItem<> (nameLabel,createIcon(eventDataIconImage)); 

        fileContextMenu.setDeleteHandle(path->{
        
            dirItem.getChildren().remove(fileItem);
        
        });
        
        fileContextMenu.setOpenHandle(path->{
            if(onFileOpenRequest!=null)
                onFileOpenRequest.handle(path);
            
        });
        
        
        
        return fileItem;

    }
    
    private TreeItem<Label> makePackageItem(File dirFile){
        
        if(dirFile == null || !dirFile.isDirectory())
            throw new RuntimeException("Invalid folder.");
        
        ContextMenu dirContextMenu = new ContextMenu();
        dirContextMenu.setOnShowing(e->{

        });

        MenuItem newEventData = new MenuItem("New eData");
        newEventData.setOnAction(e->{

            EventDataCreationWizzard wizzard = new EventDataCreationWizzard(new File(projectPath),dirFile.getName());
            wizzard.showAndWait();
            update();

        });


        dirContextMenu.getItems().addAll(newEventData);

        Label dirLabel = new Label(dirFile.getName());
        dirLabel.setContextMenu(dirContextMenu);


        TreeItem<Label> dirItem = new TreeItem<> (dirLabel,createIcon(packageIconImage)); 

        
        return dirItem;
        
    }

    private ImageView createIcon(Image image){
        
        if(image == null)
            throw new RuntimeException("Inválid Image");
        
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(iconSize);
        imageView.fitHeightProperty().bind(iconSize);
        
        return imageView;
    }

    public int getIconSize() {

        return iconSize.getValue();
    }

    public void setIconSize(int iconSize) {
        if(iconSize > 1)
            this.iconSize.setValue(iconSize);
    }
    
    
    
}

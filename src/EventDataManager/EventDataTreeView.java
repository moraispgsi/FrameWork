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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;



public class EventDataTreeView extends TreeView {

    private final File projectIconFile = new File("src/Main/Project.png");
    private final Image projectIconImage = new Image(projectIconFile.toURI().toString());
    
    private final File packageIconFile = new File("src/Main/Package.png");
    private final Image packageIconImage = new Image(packageIconFile.toURI().toString());
    
    private final File eventDataIconFile = new File("src/Main/EventData.png");
    private final Image eventDataIconImage = new Image(eventDataIconFile.toURI().toString());
    
    private String projectPath;
    
    private final TreeItem<Node> rootItem;
    private final Map<TreeItem<Node>,String> filesMap = new HashMap<>();
    private final Map<TreeItem<Node>,String> dirMap = new HashMap<>();
    
    private final Map<String,TreeItem<Node>> pathMap = new HashMap<>();

    private FileActionHandler onFileOpenRequest;
    private FileActionHandler onFileDebugRequest;
    private FileActionHandler onFileDeleteRequest;
    
    private final IntegerProperty iconSize = new SimpleIntegerProperty(15);
    
    public EventDataTreeView() {
        
        ImageView rootIcon = createIcon(projectIconImage);
        rootItem = new TreeItem<>(new Label("Project"),rootIcon);
        
        setRoot(rootItem);
        
        setOnMouseClicked(e->{
            
            if(e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2){
                TreeItem<Node> item = (TreeItem<Node>) getSelectionModel().getSelectedItem();
                if(onFileOpenRequest!= null && filesMap.containsKey(item)){
                    ProgressBarTreeItem barItem = (ProgressBarTreeItem)item;
                    barItem.showProgressBar(300,()->{
                 
                        onFileOpenRequest.handle(filesMap.get(item));

                    });
                }
            }
        });

    }

    public void setOnFileOpenRequest(FileActionHandler onFileOpenRequest) {
        this.onFileOpenRequest = onFileOpenRequest;
    }

    public void setOnFileDebugRequest(FileActionHandler onFileDebugRequest) {
        this.onFileDebugRequest = onFileDebugRequest;
    }

    public void setOnFileDeleteRequest(FileActionHandler onFileDeleteRequest) {
        this.onFileDeleteRequest = onFileDeleteRequest;
    }

    public void setProjectPath(String projectPath) {
        
        if(this.projectPath != null && this.projectPath.equals(projectPath))
            return;
        
        this.projectPath = projectPath;
        
        Platform.runLater(()->
            update()
        );
        

    }
    
    public void update(){
        
        filesMap.clear();
       
        Map<String,TreeItem<Node>> newPathMap = new HashMap<>();
        
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
            TreeItem<Node> packageItem;
            if(pathMap.containsKey(dir.getAbsolutePath())){
                packageItem = pathMap.get(dir.getAbsolutePath());
                packageItem.getChildren().clear();
            }else{
                packageItem = makePackageItem(dir);
            }
            newPathMap.put(dir.getAbsolutePath(), packageItem);
            
            
            
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
                    String fileContent;
                    try(Scanner scanner = new Scanner(file)){
                        fileContent = scanner.useDelimiter("\\Z").next();
                    }
                    

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
                
                TreeItem<Node> fileItem;
                if(pathMap.containsKey(file.getAbsolutePath())){
                    fileItem = pathMap.get(file.getAbsolutePath());
                }else{
                    fileItem = makeFileItem(file,packageItem);
                }
                    
                newPathMap.put(file.getAbsolutePath(),fileItem);
                

                packageItem.getChildren().add(fileItem);
                
                

                filesMap.put(fileItem, file.getAbsolutePath());

            }
 
            

        }
        
        pathMap.clear();
        pathMap.putAll(newPathMap);

    }
    
    private TreeItem<Node> makeFileItem(File file,TreeItem<Node> dirItem){
        
        if(file == null || file.isDirectory()){
            throw new RuntimeException("Invalid file.");
        }
        
        FileContextMenu fileContextMenu = new FileContextMenu(file.getAbsolutePath());
        
        ProgressBarTreeItem fileItem = new ProgressBarTreeItem(file.getName(),createIcon(eventDataIconImage));
        fileItem.setContextMenu(fileContextMenu);
        
        
        fileContextMenu.setDeleteHandle(path->{
        
            fileItem.showProgressBar(300,()->{
                dirItem.getChildren().remove(fileItem);
                
                if(onFileDeleteRequest !=null)
                    onFileDeleteRequest.handle(path);
            });
            
            
            
        });
        
        fileContextMenu.setOpenHandle(path->{

            fileItem.showProgressBar(300,()->{
                 if(onFileOpenRequest!=null)
                    onFileOpenRequest.handle(path);
                
            });

        });
        
        fileContextMenu.setDebugHandle(path->{
            
            fileItem.showProgressBar(300,()->{

                if(onFileDebugRequest != null)
                    onFileDebugRequest.handle(path);

            });
        });
        
        fileItem.showProgressBar(300,null);
        
        return fileItem;

    }
    
    private TreeItem<Node> makePackageItem(File dirFile){
        
        if(dirFile == null || !dirFile.isDirectory())
            throw new RuntimeException("Invalid folder.");
        
        ContextMenu dirContextMenu = new ContextMenu();
        dirContextMenu.setOnShowing(e->{

        });
        
        Label dirLabel = new Label(dirFile.getName());
        dirLabel.setContextMenu(dirContextMenu);


        TreeItem<Node> dirItem = new TreeItem<> (dirLabel,createIcon(packageIconImage)); 

        MenuItem newEventData = new MenuItem("New eData");
        newEventData.setOnAction(e->{

            EventDataCreationWizzard wizzard = new EventDataCreationWizzard(new File(projectPath),dirFile.getName());
            wizzard.showAndWait();
            
            dirItem.setExpanded(true);
            update();
        
           

        });


        dirContextMenu.getItems().addAll(newEventData);


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

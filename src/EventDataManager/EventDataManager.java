/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventDataManager;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;



public class EventDataManager extends BorderPane{

    private File projectDir;
    
    private final Label projectPath = new Label("None");
    
    private final EventDataOutputPane output = new EventDataOutputPane();
    
    private final EventDataCodingArea centerPane = new EventDataCodingArea();

    private final int iconSize = 20;
    
    private final EventDataTreeView treeView;

    public EventDataManager(Stage stage,File projectDir) {

        treeView = new EventDataTreeView();
        treeView.setProjectPath(projectDir.getAbsolutePath());
        
        treeView.setOnFileOpenRequest(e->{
        
            File file = new File(e);

            if(file.getName().toLowerCase().endsWith(".java")){

                try {
                    try (Scanner scanner = new Scanner(file)) {
                        String template = scanner.useDelimiter("\\Z").next();
                    }

                    centerPane.openTab(file.getAbsolutePath());


                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EventDataManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        
        });
        
        treeView.setOnFileDebugRequest(path->{
            
            File file = DynamicClassUtils.DynamicClassUtils.compileSource(new File(path));
            try{
                Class<?> className = DynamicClassUtils.DynamicClassUtils.loadClassFile(file);
                
                EventDataClassDebugger debugger = new EventDataClassDebugger(className);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException ex) {
                Logger.getLogger(EventDataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        treeView.setOnFileDeleteRequest(path->{
            
            centerPane.closeTab(path);
 
        });
        

        this.projectDir = projectDir;
        
        setTopBorder(stage);
        setLeftBorder();
        setCenterBorder();
        setBottomBorder();

    }
    
    private void setBottomBorder(){
        
        setBottom(output);
        
    }
    
    private void setTopBorder(Stage stage){
        
        VBox vBox = new VBox();
        
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        
        MenuItem openProject = new MenuItem("Open project");
        openProject.setOnAction(e ->{
            
            DirectoryChooser dirChooser = new DirectoryChooser();
            File projectFolder = dirChooser.showDialog(stage);
            
            if(projectFolder != null){
                
                setProjectDir(projectFolder);
                
            }
            
        }); 
        
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(e -> {
            
            EventDataTab tab = (EventDataTab) centerPane.getSelectionModel().getSelectedItem();
            
            if(tab != null)
                tab.save();
            
        });
        
        
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e ->{
            
            
            
        });

 
        menuFile.getItems().addAll(openProject,saveFile,exit);
        
        
        Menu menuEdit = new Menu("Edit");
        MenuItem createEventData = new MenuItem("Create eData");
        createEventData.setOnAction(e ->{
            
            EventDataCreationWizzard wizzard = new EventDataCreationWizzard(projectDir);
            wizzard.showAndWait();
            treeView.update();
            

        });
        menuEdit.getItems().addAll(createEventData);

        Menu menuView = new Menu("View");
        
        
 
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        
        HBox hBox = new HBox();
        Label projectPathLabel = new Label("Current project path: ");
        projectPath.setText(projectDir.getAbsolutePath());
        hBox.getChildren().addAll(projectPathLabel,projectPath);
        
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-padding: 10 20 10 20;");
        
        vBox.getChildren().addAll(menuBar,hBox);
        
        this.setTop(vBox);
        
    }
    
    private void setCenterBorder(){
        
        VBox vBox = new VBox();
        
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBox,centerPane);
        
        
        this.setCenter(vBox);
        
    }
    
    private void setLeftBorder(){
        
        VBox vBox = new VBox();
        
        vBox.getChildren().addAll(treeView);
        
        Platform.runLater(()->
            treeView.update()
        );
        

        this.setLeft(vBox);
        
    }

    public File getProjectDir() {
        return projectDir;
    }

    public void setProjectDir(File projectDir) {
        
        if(projectDir.equals(this.projectDir))
            return;
        
        File backupDir = this.projectDir;
        this.projectDir = projectDir;
        projectPath.setText(projectDir.getAbsolutePath());
        output.println("Project changed from \"" + backupDir.getAbsolutePath() + "\" to \""+ projectDir.getAbsolutePath()+"\"");
        
        treeView.setProjectPath(projectDir.getAbsolutePath());

        
    }

}

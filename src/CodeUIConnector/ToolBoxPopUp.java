/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import CodeUIConnector.SocketPane.UIStatement;
import DynamicClassUtils.DynamicClassUtils;
import DynamicClassUtils.JarClassLoader;
import Statements.GenericUI.UIGenericIOStatement;
import Statements.StatementFactory;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;
import sun.misc.Launcher;

/**
 *
 * @author Morai
 */
public class ToolBoxPopUp extends PopupWindow {
    
    private final UIFlowBoard flowBoard;
    private final VBox vBox;
    private final Accordion accordion;

    public ToolBoxPopUp(UIFlowBoard flowBoard) {
        this.flowBoard = flowBoard;

        vBox = new VBox();

        vBox.setPrefHeight(350);
        accordion = new Accordion();
        vBox.getChildren().add(accordion);

        
        
        
        ClassLoader cl = buildGeneric("RT classes",System.getProperty("java.home") + "/lib/rt.jar",this.getClass().getClassLoader());
        
        //System.out.println(System.getProperty("java.home"));
        
        //buildGeneric("JavaFX classes",System.getProperty("java.home") + "/lib/ext/jfxrt.jar",this.getClass().getClassLoader());

        getScene().setRoot(vBox);

    }

    private ClassLoader buildGeneric(String title,String pathToJar,ClassLoader cl) {

        try {
            JarClassLoader jarcl = DynamicClassUtils.loadJarClasses(pathToJar,cl);
            List<Class> classesList = new ArrayList(jarcl.getClasses());
            
            ListView<Class> list = new ListView();
            ObservableList<Class> items = FXCollections.observableArrayList(classesList);

            items.sorted((a, b) -> {

                return a.getSimpleName().compareTo(b.getSimpleName());

            });

            list.setItems(items);

            TextField filter = new TextField();
            filter.setOnKeyTyped((e) -> {

                List<Class> filteredList = classesList.stream().filter(classValue -> {
                    return classValue.getSimpleName().startsWith(filter.getText());
                }).collect(Collectors.toList());

                ObservableList<Class> itemsFiltered = FXCollections.observableArrayList(filteredList);
                itemsFiltered.sorted((a, b) -> {

                    return a.getSimpleName().compareTo(b.getSimpleName());

                });

                list.setItems(itemsFiltered);

            });

            VBox vBox1 = new VBox(filter, list);

            TitledPane titledPane = new TitledPane(title, vBox1);

            accordion.getPanes().add(titledPane);
            
            return jarcl;

        } catch (IllegalArgumentException | ClassNotFoundException ex) {
            Logger.getLogger(ToolBoxPopUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ToolBoxPopUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToolBoxPopUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    public void addStatementFactory(String title, StatementFactory factory) {

        TitledPane titledPane = buildGeneralPurpose(title, factory, flowBoard);
        accordion.getPanes().add(titledPane);

    }

    private TitledPane buildGeneralPurpose(String title, StatementFactory factory, UIFlowBoard flowBoard) {

        ListView<String> list = new ListView();

        ObservableList<String> items = FXCollections.observableArrayList(factory.getStatementsAvailable());
        list.setItems(items);
        list.getSelectionModel().selectFirst();
        list.setOnMouseClicked(e -> {

            if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {

                String statement = list.getSelectionModel().getSelectedItem();
                UIStatement uiStatement = new UIGenericIOStatement(factory.getStatement(statement));
                flowBoard.addUIStatement(uiStatement);

                uiStatement.setLayoutX(this.getX() - (flowBoard.getScene().getX() + flowBoard.getScene().getWindow().getX()));
                uiStatement.setLayoutY(this.getY() - (flowBoard.getScene().getY() + flowBoard.getScene().getWindow().getY()));
                this.hide();

            }

        });

        TitledPane titlePane = new TitledPane(title, list);
        return titlePane;
    }

}

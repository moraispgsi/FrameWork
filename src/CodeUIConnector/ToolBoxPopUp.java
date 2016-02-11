/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import CodeUIConnector.SocketPane.UIStatement;
import Statements.GenericUI.UIGenericIOStatement;
import Statements.StatementFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;

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

        vBox.setPrefHeight(200);
        accordion = new Accordion();
        vBox.getChildren().add(accordion);

        getScene().setRoot(vBox);

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

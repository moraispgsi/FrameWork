
package Main;

import CodeUIConnector.ToolBoxPopUp;
import CodeUIConnector.UIFlowBoard;
import CodeUIConnector.ZoomPane;
import Statements.Generic.GeneralPurpose.GeneralPurposeStatementsFactory;
import Statements.Generic.JavaFX.JavaFXStatementsFactory;
import Statements.GenericUI.UIConstant4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Morai
 */
public class TesteCodeUIConnector extends Application {
    
    public int methodTest(int x, boolean y){
        return 1;
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        
        //try {
            Pane pane = new Pane();
            //UIMethodFlowBoard flowBoard = new UIMethodFlowBoard("",TesteCodeUIConnector.class.getDeclaredMethod("methodTest",int.class,boolean.class) );
            //Scene scene = new Scene(flowBoard, 600, 400);
            
            UIFlowBoard flowBoard = new UIFlowBoard();
            ToolBoxPopUp toolBox = new ToolBoxPopUp(flowBoard);
            toolBox.addStatementFactory("General purpose statements",new GeneralPurposeStatementsFactory());
            toolBox.addStatementFactory("JavaFX statements",new JavaFXStatementsFactory());
            
            flowBoard.setOnMouseClicked((e)->{
                if(e.getButton() == MouseButton.SECONDARY){
     
                    toolBox.setX(e.getScreenX());
                    toolBox.setY(e.getScreenY());
                    toolBox.show(primaryStage);
                    
                } else {
                    toolBox.hide();
                }
                

            });
            
            
            Scene scene = new Scene(flowBoard,600,400);
            
            
            
            flowBoard.addUIStatement(new UIConstant4());
            
            
            
            
            primaryStage.setTitle("eData Manager");
            primaryStage.setScene(scene);
            primaryStage.show();
        /*} catch (NoSuchMethodException ex) {
            Logger.getLogger(TesteCodeUIConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TesteCodeUIConnector.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     
    }
    
     /**
     * Main
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }

        
    
}

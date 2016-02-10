
package Main;

import CodeUIConnector.ToolBoxPopUp;
import CodeUIConnector.UIFlowBoard;
import CodeUIConnector.ZoomPane;
import Statements.Generic.GeneralPurpose.ConcreteStatement1;
import Statements.Generic.GeneralPurpose.ConcreteStatement2;
import Statements.Generic.GeneralPurpose.ConcatStringStatement;
import Statements.Generic.GeneralPurpose.ConsolePrintStatement;
import Statements.Generic.JavaFX.CreateTextPaneStatement;
import Statements.Generic.GeneralPurpose.EndOutputStatement;
import Statements.Generic.GeneralPurpose.ExtensionStatement;
import Statements.Generic.GeneralPurpose.FileOpenStatement;
import Statements.Generic.GeneralPurpose.InputStreamToStringStatement;
import Statements.Generic.JavaFX.AddToChildrenStatement;
import Statements.Generic.JavaFX.CreateButtonStatement;
import Statements.Generic.GeneralPurpose.JoinedRunnablesStatement;
import Statements.Generic.JavaFX.NewSceneStatement;
import Statements.Generic.JavaFX.NewStageStatement;
import Statements.Generic.GeneralPurpose.NewThreadStatement;
import Statements.Generic.JavaFX.OpenStageWindowStatement;
import Statements.Generic.GeneralPurpose.RunnableStatement;
import Statements.Generic.GeneralPurpose.SimpleCalculatorStatement;
import Statements.Statement;
import Statements.StatementFactory;

import Statements.GenericUI.UIConstant2;
import Statements.GenericUI.UIConstant3;
import Statements.GenericUI.UIGenericIOStatement;
import Statements.GenericUI.UIGenericStatement;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
            ToolBoxPopUp toolBox = new ToolBoxPopUp();
            
            flowBoard.setOnMouseClicked((e)->{
                if(e.getButton() == MouseButton.SECONDARY)
                    toolBox.show(primaryStage);
                
            });
            
            Scene scene = new Scene(flowBoard,600,400);
            
            //flowBoard.addUIStatement( new UIConstant("Constants"));
            //flowBoard.addUIStatement( new UIConstant("Constants"));
            //flowBoard.addUIStatement(new UIGenericIOStatement(new InputStreamToStringStatement()));
            
            //flowBoard.addUIStatement(new UIConstant2("File paths"));
            
            //flowBoard.addUIStatement(new UIGenericIOStatement(new FileOpenStatement()));
            //flowBoard.addUIStatement(new UIGenericIOStatement(new ExtensionStatement(Object.class)));
            //flowBoard.addUIStatement(new UIGenericIOStatement(new SimpleCalculatorStatement()));
            //flowBoard.addUIStatement(new UIGenericIOStatement(new SimpleCalculatorStatement()));
            //flowBoard.addUIStatement(new UIGenericIOStatement(new ConcatStringStatement()));
            //flowBoard.addUIStatement(new UIGenericIOStatement(new ConcatStringStatement()));
            
            /*
            Teste 2
            
            flowBoard.addUIStatement(new UIGenericIOStatement(new InputStreamToStringStatement()));
            flowBoard.addUIStatement(new UIConstant2("File paths"));
            flowBoard.addUIStatement(new UIGenericIOStatement(new FileOpenStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new  ConsolePrintStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(StatementFactory.getStatement("ConsolePrintStatement")));
            flowBoard.addUIStatement(new UIGenericIOStatement(new NewThreadStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new NewThreadStatement()));
            flowBoard.addUIStatement(new UIConstant3("Runnables"));
            flowBoard.addUIStatement(new UIGenericIOStatement(new EndOutputStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new EndOutputStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new RunnableStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new JoinedRunnablesStatement()));
            */
            
            flowBoard.addUIStatement(new UIConstant3("Runnables"));
            flowBoard.addUIStatement(new UIGenericIOStatement(new RunnableStatement()));
            flowBoard.addUIStatement(new UIConstant2("File paths"));
            flowBoard.addUIStatement(new UIGenericIOStatement(new FileOpenStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new InputStreamToStringStatement()));
            
            flowBoard.addUIStatement(new UIGenericIOStatement(new CreateTextPaneStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new OpenStageWindowStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new NewSceneStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new NewStageStatement()));
            
            flowBoard.addUIStatement(new UIGenericIOStatement(new CreateButtonStatement()));
             flowBoard.addUIStatement(new UIGenericIOStatement(new AddToChildrenStatement()));
            
            flowBoard.addUIStatement(new UIGenericIOStatement(new SimpleCalculatorStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new SimpleCalculatorStatement()));
            
            
            
            
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

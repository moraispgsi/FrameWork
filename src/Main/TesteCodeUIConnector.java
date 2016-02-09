
package Main;

import CodeUIConnector.UIFlowBoard;
import Statements.Generic.ConcreteStatement1;
import Statements.Generic.ConcreteStatement2;
import Statements.Generic.ConcatStringStatement;
import Statements.Generic.ConsolePrintStatement;
import Statements.Generic.CreateTextPaneStatement;
import Statements.Generic.EndOutputStatement;
import Statements.Generic.ExtensionStatement;
import Statements.Generic.FileOpenStatement;
import Statements.Generic.InputStreamToStringStatement;
import Statements.Generic.JoinedRunnablesStatement;
import Statements.Generic.NewSceneStatement;
import Statements.Generic.NewStageStatement;
import Statements.Generic.NewThreadStatement;
import Statements.Generic.OpenStageWindowStatement;
import Statements.Generic.RunnableStatement;
import Statements.Generic.SimpleCalculatorStatement;
import Statements.Generic.Statement;
import Statements.Generic.StatementFactory;
import Statements.GenericUI.UIConstant;
import Statements.GenericUI.UIConstant2;
import Statements.GenericUI.UIConstant3;
import Statements.GenericUI.UIGenericIOStatement;
import Statements.GenericUI.UIGenericStatement;
import javafx.application.Application;
import javafx.scene.Scene;
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
            
            flowBoard.addUIStatement(new UIConstant2("File paths"));
            flowBoard.addUIStatement(new UIGenericIOStatement(new FileOpenStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new InputStreamToStringStatement()));
            
            flowBoard.addUIStatement(new UIGenericIOStatement(new CreateTextPaneStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new OpenStageWindowStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new NewSceneStatement()));
            flowBoard.addUIStatement(new UIGenericIOStatement(new NewStageStatement()));
            
            
            
            
            
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic.GeneralPurpose;

import Statements.StatementFactory;
import Statements.Generic.JavaFX.NewStageStatement;
import java.util.Arrays;

/**
 *
 * @author Morai
 */
public class GeneralPurposeStatementsFactory extends StatementFactory {

    public GeneralPurposeStatementsFactory() {
        
        super(Arrays.asList(
                
            ConcatStringStatement.class,
            ConsolePrintStatement.class,
            FileOpenStatement.class,
            InputStreamToStringStatement.class,
            JoinedRunnablesStatement.class,
            NewThreadStatement.class,
            RunnableStatement.class,
            SimpleCalculatorStatement.class,
            EndOutputStatement.class,
            ConcatEndOutputsStatement.class,
            StringToIntegerStatement.class
            
        ));

    }

}

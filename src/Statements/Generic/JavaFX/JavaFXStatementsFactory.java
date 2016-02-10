/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic.JavaFX;

import Statements.StatementFactory;
import java.util.Arrays;

/**
 *
 * @author Morai
 */
public class JavaFXStatementsFactory extends StatementFactory {

    public JavaFXStatementsFactory() {
        
        super(Arrays.asList(
            AddToChildrenStatement.class,
            CreateButtonStatement.class,
            CreateTextPaneStatement.class,
            NewSceneStatement.class,
            NewStageStatement.class,
            OpenStageWindowStatement.class)
        );

    }

}

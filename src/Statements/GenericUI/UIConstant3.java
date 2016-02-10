/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.GenericUI;

import CodeUIConnector.ParamSockets.Controller.ParamConstantOutput;
import CodeUIConnector.ParamSockets.Controller.ParamOutput;
import CodeUIConnector.SocketPane.UIStatement;
import Statements.Generic.GeneralPurpose.SimpleCalculatorStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morai
 */
public class UIConstant3 extends UIStatement {

    public UIConstant3(String title) {
        super(title);

        Set<Runnable> values1 = new HashSet();
        Runnable runnable1 = new Runnable() { 
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Teste1");
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIConstant3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public String toString() {
                return "Teste1";
            }
        };
        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Teste2");
                } catch (InterruptedException ex) {
                    Logger.getLogger(UIConstant3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public String toString() {
                return "Teste2";
            }
     
        };

        values1.add(runnable1);
        values1.add(runnable2);

        ParamOutput paramOutput1 = new ParamConstantOutput(Runnable.class, values1);
        this.addOutputParam(paramOutput1);

    }

}

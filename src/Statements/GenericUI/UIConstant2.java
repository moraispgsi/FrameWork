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

/**
 *
 * @author Morai
 */
public class UIConstant2 extends UIStatement{

    public UIConstant2(String title) {
        super(title);
        
        Set<String> values1 = new TreeSet();
        values1.add("C:\\Users\\Morai\\Desktop\\Teste.txt");
        values1.add("C:\\Users\\Morai\\Desktop\\Teste2.txt");

        ParamOutput paramOutput1 = new ParamConstantOutput(String.class, values1);
        this.addOutputParam(paramOutput1);
        
        
        Set<Double> values2 = new TreeSet();
        values2.add(6d);
        values2.add(8d);
        values2.add(10d);
        values2.add(300d);
        
        ParamOutput paramOutput2 = new ParamConstantOutput(Double.class, values2);
        this.addOutputParam(paramOutput2);
        
        
        Set<Double> values3 = new TreeSet();
        values3.add(1d);
        values3.add(3d);
        values3.add(5d);
        values3.add(400d);
        
        ParamOutput paramOutput3 = new ParamConstantOutput(Double.class, values3);
        this.addOutputParam(paramOutput3);
        
        
        
    }
    
    
            
    
}

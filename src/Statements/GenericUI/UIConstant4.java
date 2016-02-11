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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Morai
 */
public class UIConstant4 extends UIStatement{

    public UIConstant4() {
        super("Placeholder");
        
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
        
        
        Set<XYChart.Series> values3 = new HashSet();
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        values3.add(series);
        
        ParamOutput paramOutput3 = new ParamConstantOutput(XYChart.Series.class, values3);
        this.addOutputParam(paramOutput3);
        
        
        Set<Class> values4 = new HashSet();
        values4.add(List.class);
        values4.add(Set.class); 
        
        
        ParamOutput paramOutput4 = new ParamConstantOutput(Class.class, values4);
        this.addOutputParam(paramOutput4);
        
        
    }
    
    
            
    
}

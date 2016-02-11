/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements.Generic.JavaFX;

import Statements.BaseInput;
import Statements.BaseOutput;
import Statements.BaseStatement;
import Statements.ExecutingInput;
import Statements.ExecutingOutput;
import Statements.ExecutingStatement;
import Statements.Input;
import Statements.Output;
import Statements.OutputNotAvailableException;
import Statements.Statement;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 *
 * @author Morai
 */
public class NewXYChartStatement implements Statement {

    private BaseStatement base;
    private final String seriesInput = "series";
    private final String titleInput = "title";

    public NewXYChartStatement() {
        base = new BaseStatement("Criar XYChart", generateOutputs(), generateInputs());
    }

    private SortedMap<String, Output> generateOutputs() {

        Output<XYChart> constantValue1 = new BaseOutput<XYChart>("chart", XYChart.class) {

            @Override
            public ExecutingOutput<XYChart> getExecutionInstance() {

                try {
                    XYChart.Series series = (XYChart.Series) base.mapInputs().get(seriesInput).getExecutingInput().getOutput().getValue();
                    String title = (String) base.mapInputs().get(titleInput).getExecutingInput().getOutput().getValue();

                    //defining the axes
                    final NumberAxis xAxis = new NumberAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    //xAxis.setLabel("PlaceHolder");
                    //creating the chart
                    final XYChart<Number, Number> lineChart
                            = new LineChart<>(xAxis, yAxis);

                    lineChart.setTitle(title);

                    lineChart.getData().add(series);

                    return () -> {
                        return lineChart;
                    };
                } catch (OutputNotAvailableException ex) {
                    Logger.getLogger(NewStageStatement.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

        };

        SortedMap<String, Output> mapOutputs = new TreeMap();
        mapOutputs.put(constantValue1.getName(), constantValue1);
        return mapOutputs;
    }

    private SortedMap<String, Input> generateInputs() {

        Input input1 = new BaseInput(seriesInput, XYChart.Series.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        Input input2 = new BaseInput(titleInput, String.class) {

            @Override
            public ExecutingInput getExecutingInput() {
                return () -> {
                    return this.getOutput().getExecutionInstance();
                };
            }

        };

        SortedMap<String, Input> mapInputs = new TreeMap();
        mapInputs.put(input1.getName(), input1);
        mapInputs.put(input2.getName(), input2);

        return mapInputs;

    }

    @Override

    public Set<Output> getOutputs() {
        return base.getOutputs();
    }

    @Override
    public Set<Input> getInputs() {
        return base.getInputs();
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public ExecutingStatement getExecutingInstance() {
        return () -> {

        };
    }

}

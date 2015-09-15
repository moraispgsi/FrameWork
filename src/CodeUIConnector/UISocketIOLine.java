/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Point2D;

/**
 *
 * @author Morai
 */
public class UISocketIOLine extends HorizontalCurvedLine {
    
    private UISocket inputSocket;
    private UISocket outputSocket;
    
    public UISocketIOLine(ObjectProperty<Point2D> sceneBoundsProperty,UISocket inputSocket,UISocket outputSocket){
        super();
        
        this.inputSocket = inputSocket;
        this.outputSocket = outputSocket;
        
        DoubleBinding startX = new DoubleBinding(){

            {
                bind(sceneBoundsProperty,inputSocket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = inputSocket.getPlugBoundsProperty().getValue().getMaxX();
                double socketMin = inputSocket.getPlugBoundsProperty().getValue().getMinX();
                double center = socketMin + (socketMax - socketMin)/2;


                return center - sceneBoundsProperty.getValue().getX();

            }

        };

        DoubleBinding startY = new DoubleBinding(){

            {
                bind(sceneBoundsProperty,inputSocket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = inputSocket.getPlugBoundsProperty().getValue().getMaxY();
                double socketMin = inputSocket.getPlugBoundsProperty().getValue().getMinY();
                double center = socketMin + (socketMax - socketMin)/2;

                return center - sceneBoundsProperty.getValue().getY();
            }

        };

        DoubleBinding endX = new DoubleBinding(){

            {
                bind(sceneBoundsProperty,outputSocket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {

                double socketMax = outputSocket.getPlugBoundsProperty().getValue().getMaxX();
                double socketMin = outputSocket.getPlugBoundsProperty().getValue().getMinX();
                double center = socketMin + (socketMax - socketMin)/2;

                return center - sceneBoundsProperty.getValue().getX();
            }

        };

        DoubleBinding endY = new DoubleBinding(){

            {
                bind(sceneBoundsProperty,outputSocket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = outputSocket.getPlugBoundsProperty().getValue().getMaxY();
                double socketMin = outputSocket.getPlugBoundsProperty().getValue().getMinY();
                double center = socketMin + (socketMax - socketMin)/2;

                return center - sceneBoundsProperty.getValue().getY();
            }

        };
        
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
   
    }
    
    public UISocket getInputSocket() {
        return inputSocket;
    }

    public UISocket getOutputSocket() {
        return outputSocket;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;

import CodeUIConnector.SocketPane.UISocket;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Transform;
import org.apache.commons.math3.geometry.euclidean.twod.*;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class HorizontalCurvedLine extends CubicCurve {

    private ObjectProperty<Point2D> sceneBoundsProperty = new SimpleObjectProperty<>();

    private ChangeListener<Transform> parentTransformListener;

    public HorizontalCurvedLine() {
        
        setDefaultStyle();
        
        sceneBoundsProperty.set(Point2D.ZERO);

        parentProperty().addListener((ChangeListener<Parent>) (ObservableValue<? extends Parent> observable, Parent oldValue, Parent newValue) -> {

            if (newValue == null) {
                return;
            }

            if (oldValue != null) {
                newValue.localToSceneTransformProperty().removeListener(parentTransformListener);
            }

            parentTransformListener = (o2, oldValue2, newValue2) -> {

                sceneBoundsProperty.setValue(newValue.localToScene(0, 0));

            };

            newValue.localToSceneTransformProperty().addListener(parentTransformListener);

            sceneBoundsProperty.setValue(newValue.localToScene(0, 0));
        });

        DoubleBinding controlX1 = new DoubleBinding() {

            {
                bind(startXProperty(), startYProperty(), endXProperty(), endYProperty());
            }

            @Override
            protected double computeValue() {
                return computeControl1().getX();
            }

        };

        DoubleBinding controlY1 = new DoubleBinding() {

            {
                bind(startXProperty(), startYProperty(), endXProperty(), endYProperty());
            }

            @Override
            protected double computeValue() {
                return computeControl1().getY();
            }

        };

        DoubleBinding controlX2 = new DoubleBinding() {

            {
                bind(startXProperty(), startYProperty(), endXProperty(), endYProperty());
            }

            @Override
            protected double computeValue() {
                return computeControl2().getX();
            }

        };

        DoubleBinding controlY2 = new DoubleBinding() {

            {
                bind(startXProperty(), startYProperty(), endXProperty(), endYProperty());
            }

            @Override
            protected double computeValue() {

                return computeControl2().getY();
            }

        };

        this.controlX1Property().bind(controlX1);
        this.controlY1Property().bind(controlY1);

        this.controlX2Property().bind(controlX2);
        this.controlY2Property().bind(controlY2);

    }

    private void setDefaultStyle(){
        
        this.setStroke(Color.LIGHTBLUE);
        this.setStrokeWidth(2);
        this.setStrokeLineCap(StrokeLineCap.ROUND);
        this.setFill(new Color(0, 0, 0, 0));
        
    }

    public void bindLeftSocket(UISocket socket) {

        DoubleBinding bindX = new DoubleBinding() {

            {
                bind(sceneBoundsProperty, socket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = socket.getPlugBoundsProperty().getValue().getMaxX();
                double socketMin = socket.getPlugBoundsProperty().getValue().getMinX();
                double center = socketMin + (socketMax - socketMin) / 2;

                return center - sceneBoundsProperty.getValue().getX();

            }

        };

        DoubleBinding bindY = new DoubleBinding() {

            {
                bind(sceneBoundsProperty, socket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = socket.getPlugBoundsProperty().getValue().getMaxY();
                double socketMin = socket.getPlugBoundsProperty().getValue().getMinY();
                double center = socketMin + (socketMax - socketMin) / 2;

                return center - sceneBoundsProperty.getValue().getY();
            }

        };

        endXProperty().bind(bindX);
        endYProperty().bind(bindY);

    }

    public void bindRightSocket(UISocket socket) {

        DoubleBinding bindX = new DoubleBinding() {

            {
                bind(sceneBoundsProperty, socket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = socket.getPlugBoundsProperty().getValue().getMaxX();
                double socketMin = socket.getPlugBoundsProperty().getValue().getMinX();
                double center = socketMin + (socketMax - socketMin) / 2;

                return center - sceneBoundsProperty.getValue().getX();

            }

        };

        DoubleBinding bindY = new DoubleBinding() {

            {
                bind(sceneBoundsProperty, socket.getPlugBoundsProperty());
            }

            @Override
            protected double computeValue() {
                double socketMax = socket.getPlugBoundsProperty().getValue().getMaxY();
                double socketMin = socket.getPlugBoundsProperty().getValue().getMinY();
                double center = socketMin + (socketMax - socketMin) / 2;

                return center - sceneBoundsProperty.getValue().getY();
            }

        };

        startXProperty().bind(bindX);
        startYProperty().bind(bindY);

    }

    protected Vector2D computeControl1() {

        Vector2D A = new Vector2D(startXProperty().getValue(), startYProperty().getValue());
        Vector2D B = new Vector2D(endXProperty().getValue(), endYProperty().getValue());

        Vector2D direction = B.subtract(A);

        Double distance = (new Vector2D(1, 0)).dotProduct(direction);

        if (direction.equals(Vector2D.ZERO) || distance == 0) {
            return A;
        }

        direction = new Vector2D(-1, 0).scalarMultiply(distance).normalize();

        direction = direction.scalarMultiply(distance / 2);

        Double x = direction.getX() + startXProperty().getValue();
        Double y = direction.getY() + startYProperty().getValue();

        return new Vector2D(x, y);

    }

    protected Vector2D computeControl2() {

        Vector2D A = new Vector2D(startXProperty().getValue(), startYProperty().getValue());
        Vector2D B = new Vector2D(endXProperty().getValue(), endYProperty().getValue());

        Vector2D direction = A.subtract(B);

        Double distance = (new Vector2D(1, 0)).dotProduct(direction);

        if (direction.equals(Vector2D.ZERO) || distance == 0) {
            return B;
        }

        direction = new Vector2D(1, 0).scalarMultiply(distance).normalize();

        direction = direction.scalarMultiply(distance / 2);

        Double x = direction.getX() + endXProperty().getValue();
        Double y = direction.getY() + endYProperty().getValue();

        return new Vector2D(x, y);

    }

}

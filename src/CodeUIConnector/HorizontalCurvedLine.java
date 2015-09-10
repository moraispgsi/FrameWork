/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;
import org.apache.commons.math3.geometry.euclidean.twod.*;


/**
 *
 * @author Ricardo José Horta Morais
 */
public class HorizontalCurvedLine extends CubicCurve {
    

    private final double anguloRadianos = Math.PI /8;


    
    
    public HorizontalCurvedLine() {
        


        DoubleBinding controlX1 = new DoubleBinding(){

            {
                bind(startXProperty(),startYProperty(),endXProperty(),endYProperty());
            }
            

            @Override
            protected double computeValue() {
                return computeControl1().getX();
            }
            
        };
        
        DoubleBinding controlY1 = new DoubleBinding(){

            {
                bind(startXProperty(),startYProperty(),endXProperty(),endYProperty());
            }
            

            @Override
            protected double computeValue() {
                return computeControl1().getY();
            }
            
        };
        
        DoubleBinding controlX2 = new DoubleBinding(){

            {
                bind(startXProperty(),startYProperty(),endXProperty(),endYProperty());
            }
            

            @Override
            protected double computeValue() {
                return computeControl2().getX();
            }
            
        };
        
        DoubleBinding controlY2 = new DoubleBinding(){

            {
                bind(startXProperty(),startYProperty(),endXProperty(),endYProperty());
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
    
    
    protected Vector2D computeControl1() {


            Vector2D A = new Vector2D(startXProperty().getValue(),startYProperty().getValue());
            Vector2D B = new Vector2D(endXProperty().getValue(),endYProperty().getValue());
            
            Vector2D direcao = B.subtract(A);

            Double distancia = (new Vector2D(1,0)).dotProduct(direcao);
            
            if(direcao.equals(Vector2D.ZERO) || distancia == 0)
                return A;
            
            direcao = new Vector2D(-1,0).scalarMultiply(distancia).normalize();

            direcao = direcao.scalarMultiply(distancia/2);

            Double x = direcao.getX() + startXProperty().getValue();
            Double y = direcao.getY() + startYProperty().getValue();
            
            
            return new Vector2D(x,y);
        
   }
        
    
    
    protected Vector2D computeControl2() {
        

            Vector2D A = new Vector2D(startXProperty().getValue(),startYProperty().getValue());
            Vector2D B = new Vector2D(endXProperty().getValue(),endYProperty().getValue());

            Vector2D direcao = A.subtract(B);

            Double distancia = (new Vector2D(1,0)).dotProduct(direcao);
            
            if(direcao.equals(Vector2D.ZERO) || distancia == 0)
                return B;
            
            direcao = new Vector2D(1,0).scalarMultiply(distancia).normalize();

            direcao = direcao.scalarMultiply(distancia/2);

            Double x = direcao.getX() + endXProperty().getValue();
            Double y = direcao.getY() + endYProperty().getValue();

            return new Vector2D(x,y);
        
    }
    
   
    

}

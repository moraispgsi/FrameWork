/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeUIConnector;


import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.CubicCurve;
import org.apache.commons.math3.geometry.euclidean.twod.*;


/**
 *
 * @author Ricardo José Horta Morais
 */
public class HorizontalCurvedLine extends CubicCurve {

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
            
            Vector2D direction = B.subtract(A);

            Double distance = (new Vector2D(1,0)).dotProduct(direction);
            
            if(direction.equals(Vector2D.ZERO) || distance == 0)
                return A;
            
            direction = new Vector2D(-1,0).scalarMultiply(distance).normalize();

            direction = direction.scalarMultiply(distance/2);

            Double x = direction.getX() + startXProperty().getValue();
            Double y = direction.getY() + startYProperty().getValue();
            
            
            return new Vector2D(x,y);
        
   }
        
    
    
    protected Vector2D computeControl2() {
        

            Vector2D A = new Vector2D(startXProperty().getValue(),startYProperty().getValue());
            Vector2D B = new Vector2D(endXProperty().getValue(),endYProperty().getValue());

            Vector2D direction = A.subtract(B);

            Double distance = (new Vector2D(1,0)).dotProduct(direction);
            
            if(direction.equals(Vector2D.ZERO) || distance == 0)
                return B;
            
            direction = new Vector2D(1,0).scalarMultiply(distance).normalize();

            direction = direction.scalarMultiply(distance/2);

            Double x = direction.getX() + endXProperty().getValue();
            Double y = direction.getY() + endYProperty().getValue();

            return new Vector2D(x,y);
        
    }
    
   
    

}

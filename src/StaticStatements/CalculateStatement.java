/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticStatements;

import java.util.Arrays;

/**
 *
 * @author Morai
 */
public class CalculateStatement extends GenericStatement{

    public CalculateStatement() {
        super(Arrays.asList("Next"), 
              Arrays.asList("Number1","Number2"), 
              Arrays.asList("Multiplication","Addition"));
    }
    
    public void setNextStatement(GenericStatement statement){
        invokes.get("Next").setStatement(statement);
    }
    
    public void setNumber1(StaticValue value){
        
        if(!value.getType().equals(StaticInteger.staticType()))
            throw new RuntimeException("Static type does not match.");
        
        valuesInput.replace("Number1", value);
        
        refreshValues();
    }
    
    public void setNumber2(StaticValue value){
        
        if(!value.getType().equals(StaticInteger.staticType()))
            throw new RuntimeException("Static type does not match.");
        
        valuesInput.replace("Number2", value);
        
        refreshValues();
    }
    
    private void refreshValues(){

        StaticInteger a = (StaticInteger)valuesInput.get("Number1");
        StaticInteger b = (StaticInteger)valuesInput.get("Number2");
        
        if(a == null || b == null)
            return;
        
        StaticValue multiplication = new StaticInteger(a.getIntegerValue() * b.getIntegerValue());
        StaticValue addition = new StaticInteger(a.getIntegerValue() + b.getIntegerValue());
        
    }
    
    
    @Override
    public boolean missingDependency() {
        return (valuesInput.get("Number1") == null) || 
                (valuesInput.get("Number2") == null) ||
                invokes.get("Next").getStatement().missingDependency();
    }

    @Override
    public String generate() {
        
        if(missingDependency())
            return "";
        
        return invokes.get("Next").getStatement().generate();

    }
    
}

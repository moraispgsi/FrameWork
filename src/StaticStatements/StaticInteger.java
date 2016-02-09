/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticStatements;

/**
 *
 * @author Morai
 */
public class StaticInteger implements StaticValue{

    private Integer value;

    public StaticInteger(Integer value) {
        this.value = value;
    }

    public Integer getIntegerValue() {
        return value;
    }

    @Override
    public StaticType getType() {
        return new StaticType("Integer");
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    public static StaticType staticType() {
        return new StaticType("Integer");
    }
    
}

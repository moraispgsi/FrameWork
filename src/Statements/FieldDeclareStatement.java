/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author Ricardo José Horta Morais
 */
public class FieldDeclareStatement implements Statement , FieldReference{
    
    private final Class<?> type;
    private final String name;
    private ReturningStatement returningStatement;
    private Statement nextStatement;

    public FieldDeclareStatement(Class<?> type, String name) {
        this.type = type;
        this.name = name;
    }

    
    @Override
    public boolean missingDependency() {
        
        return (returningStatement != null && returningStatement.missingDependency()) || (nextStatement != null && nextStatement.missingDependency());
        
    }

    @Override
    public String generateJavaCode() {
        
        if(missingDependency())
            throw new RuntimeException("Missing Dependency.");
        
        String result = type.getSimpleName() + " " + name;
        
        if(returningStatement == null){
            
            if(type.isPrimitive())
                result += ";";
            else
                result += " = null;";
            
        }else {
            
            result += " = ";
            if(!returningStatement.getReturnType().equals(type)){
                result += "(" + type.getSimpleName() + ") ";
            }
            
            result += returningStatement.generateJavaCode() + ";";

        }
        
        return result + "\n" + (nextStatement == null ? "":nextStatement.generateJavaCode());

    }

    public void setReturningStatement(ReturningStatement returningStatement) {
        this.returningStatement = returningStatement;
    }

    public void setNextStatement(Statement nextStatement) {
        this.nextStatement = nextStatement;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

}

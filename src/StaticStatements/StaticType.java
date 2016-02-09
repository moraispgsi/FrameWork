/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StaticStatements;

import java.util.Objects;

/**
 *
 * @author Morai
 */
public final class StaticType {
    private final String type;

    public StaticType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(!(obj instanceof StaticType))
            return false;
        
        
        return type.equals(((StaticType)obj).type);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.type);
        return hash;
    }

}

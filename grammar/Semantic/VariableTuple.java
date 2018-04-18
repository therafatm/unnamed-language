package Semantic;

import MyTypes.*;
import AST.*;

public class VariableTuple {

    public Identifier name;
    public MyType returnType;
    public boolean isDefined;

	public VariableTuple(Identifier n, MyType type){
        name = n; 
        returnType = type;
        isDefined = false; 
    }
    
    public void setIsDefined(){
        isDefined = true;
    }

    public String toString(){
        return name + "\n" + returnType + "\n" + isDefined + "\n--\n";
    }
}


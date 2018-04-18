package Semantic;

import MyTypes.*;
import Semantic.VariableTuple;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FunctionEnvironment {

    public String functionName;
    public MyType returnType;
    public boolean hasReturnStatement;
    public LinkedHashMap<String, MyType> parameters = new LinkedHashMap<String, MyType>();
    public HashMap<String, VariableTuple> variables = new HashMap<String, VariableTuple>();
	
	public FunctionEnvironment(String name, MyType type){
		functionName = name;
        returnType = type;
        hasReturnStatement = false;
	}
}


package IR;

import MyTypes.*;
import Semantic.FunctionEnvironment;
import Semantic.VariableTuple;

import java.util.HashMap;
import java.util.ArrayList;

public class IRFunctionEnvironment extends FunctionEnvironment {

    public String functionIRSignature;
    public ArrayList<String> IRInstructionList = new ArrayList<String>();
    public ArrayList<String> ASMInstructionList = new ArrayList<String>();
    public HashMap<String, Temporary> varsToTemp = new HashMap<String, Temporary>();
    public HashMap<String, Temporary> paramsToTemp = new HashMap<String, Temporary>();
    public TempManager Hagrid = new TempManager();
    public int paramOffset = 0;

    public IRFunctionEnvironment(String name, MyType type){
        super(name, type); 
    }
}


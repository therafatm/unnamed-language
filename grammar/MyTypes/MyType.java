package MyTypes;
import AST.Visitor;
import Semantic.*;

public class MyType {
    public int lineNumber;
    public int offset;
    public String t;
    public boolean isArray = false;
    public int arraySize = 0;

    public MyType (String v, int l, int o){
        t = v;
        lineNumber = l;
        offset = o;
    }

    public void setArraySize(String i) {
        arraySize = Integer.parseInt(i);
        return;
    }

    public void setIsArray() {
        isArray = true;
        return;
    }

    public boolean equals(MyType type) {
        return type.t.equals(t) && type.isArray == isArray && type.arraySize == arraySize;
    }
 
    public boolean isSubtypeOf(MyType type) {
        return false;
    }

	public void accept (Visitor v) {
		v.visit(this);
	}
	
	public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
    }
    
    public String toString () {
       return isArray ? t + "[" + arraySize + "]" : t;
    }
}


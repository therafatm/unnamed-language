package AST;
import Semantic.*;
import IR.*;

import MyTypes.MyType;

public class ArrayReference extends Expression {
	
	public Identifier id;
	public Expression expr;
	public int lineNumber;
	public int offset;

	public ArrayReference(Identifier i, Expression e){
		id = i;
		expr = e;	
		lineNumber = id.lineNumber;
		offset = id.offset;
	}
    
	public void accept (Visitor v) {
       v.visit(this);
   }

	public MyType accept (TypeVisitor v) throws SemanticException {
		return v.visit(this);
	}
	
	public Temporary accept(IRVisitor v) throws IRException {
		return v.visit(this);
	}
}

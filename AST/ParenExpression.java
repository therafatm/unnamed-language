package AST;
import Semantic.*;
import MyTypes.MyType;
import IR.*;

public class ParenExpression extends Expression {
	public Expression expr;
	public int lineNumber;
	public int offset;

	public ParenExpression(Expression e, int line, int pos){
		expr = e;
		lineNumber = line;
		offset = pos;
	}
    
	public void accept (Visitor v) {
		v.visit(this);
	}
	
	public MyType accept (TypeVisitor v) throws SemanticException {
		return v.visit(this);
	}

	public Temporary accept (IRVisitor v) throws IRException {
		return v.visit(this);
	}
}

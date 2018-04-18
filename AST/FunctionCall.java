package AST;
import Semantic.*;
import MyTypes.MyType;
import IR.*;

public class FunctionCall extends Expression {
	
	public Identifier id;
	public ExpressionList exprList;
	public int lineNumber;
	public int offset;

	public FunctionCall(Identifier i, ExpressionList el){
		id = i;
		exprList = el;
		lineNumber = i.lineNumber;
		offset = i.offset;
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

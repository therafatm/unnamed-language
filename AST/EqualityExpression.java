package AST;
import Semantic.*;
import MyTypes.MyType;
import IR.*;

public class EqualityExpression extends Expression {
	public String operand = "=="; 
	public Expression left;
	public Expression right;
	public int lineNumber;
	public int offset;

	public EqualityExpression(Expression l, Expression r, int line, int pos){
		left = l;
		right = r;
		lineNumber = line;
		offset = pos;
	}

	public void accept(Visitor v){
		v.visit(this);	
	}

	public MyType accept (TypeVisitor v) throws SemanticException {
		return v.visit(this);
	}

	public Temporary accept (IRVisitor v) throws IRException {
		return v.visit(this);
	}
}

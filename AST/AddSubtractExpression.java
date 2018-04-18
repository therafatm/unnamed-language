package AST;
import Semantic.*;
import IR.IRVisitor;
import MyTypes.MyType;
import IR.*;

public class AddSubtractExpression extends Expression {
	
	public String operand; 
	public Expression left;
	public Expression right;
	public int lineNumber;
	public int offset;

	public AddSubtractExpression(String o, Expression l, Expression r, int line, int pos){
		operand = o;	
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

	public Temporary accept(IRVisitor v) throws IRException {
		return v.visit(this);
	}
}

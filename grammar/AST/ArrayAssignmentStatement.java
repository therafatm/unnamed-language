package AST;
import Semantic.*;
import IR.*;

public class ArrayAssignmentStatement extends Statement {
    
	public Expression e1;
	public Identifier id;
	public Expression e2;

    public ArrayAssignmentStatement(Identifier i, Expression inner, Expression outer) {
        e1 = inner;
		id = i;
		e2 = outer;
    }
 
	public void accept (Visitor v) {
		v.visit(this);
	}

	public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
	}

	public void accept(IRVisitor v) throws IRException {
		v.visit(this);
	}
}

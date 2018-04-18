package AST;
import Semantic.*;
import IR.*;

public class IdAssignmentStatement extends Statement {

    public Expression expr;
	public Identifier id;

    public IdAssignmentStatement (Identifier i, Expression e) {
        expr = e;
		id = i;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

	public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
    }
    
    public void accept (IRVisitor v) throws IRException {
		v.visit(this);
	}
}

package AST;
import Semantic.*;
import IR.*;

public class ExpressionStatement extends Statement {

	public Expression expr;

    public ExpressionStatement(Expression e) {
        expr = e;
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

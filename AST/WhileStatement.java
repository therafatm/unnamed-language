package AST;
import Semantic.*;
import IR.*;

public class WhileStatement extends Statement {

    public Expression expr;
	public Block b;		

    public WhileStatement (Expression e, Block b1) {
        expr = e;
		b = b1;
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

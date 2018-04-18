package AST;
import Semantic.*;
import IR.*;

public class IfStatement extends Statement {

    public Expression expr;
    public Block b1;

    public IfStatement (Expression e, Block block1) {
        expr = e;
        b1 = block1;
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

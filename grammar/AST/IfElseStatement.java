package AST;
import Semantic.*;
import IR.*;

public class IfElseStatement extends Statement {

    public Expression expr;
	public Block b1;
	public Block b2;

    public IfElseStatement (Expression e, Block block1, Block block2) {
        expr = e;
		b1 = block1;
		b2 = block2;
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


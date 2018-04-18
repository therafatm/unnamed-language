package AST;
import Semantic.*;
import IR.*;

public class ReturnStatement extends Statement {

    public Expression expr = null;
    public int lineNumber;
    public int offset;

    public ReturnStatement (Expression e, int line, int pos) {
        expr = e;
        lineNumber = line;
        offset = pos;
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

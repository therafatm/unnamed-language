package AST;
import Semantic.*;
import IR.*;

public class PrintStatement extends Statement {
	
	public Expression expr;
	
	public PrintStatement (Expression e){
		expr = e;
	}
	
	public void accept (Visitor v){
		v.visit(this);
	}

	public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
	}

    public void accept (IRVisitor v) throws IRException {
		v.visit(this);
	}
}

	

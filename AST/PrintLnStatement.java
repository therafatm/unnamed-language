package AST;
import Semantic.*;
import IR.*;

public class PrintLnStatement extends Statement {
	
	public Expression expr;
	
	public PrintLnStatement (Expression e){
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

	

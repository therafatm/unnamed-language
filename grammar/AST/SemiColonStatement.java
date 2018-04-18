package AST;
import Semantic.*;
import IR.*;

public class SemiColonStatement extends Statement {

   	public String value;

    public SemiColonStatement() {
        value = ";"; 
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

package AST;
import Semantic.*;
import MyTypes.*;
import IR.*;

public class VariableDeclaration {
	
	public MyType ctype;
	public Identifier id;

	public VariableDeclaration (MyType ct, Identifier i){
		ctype = ct;
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

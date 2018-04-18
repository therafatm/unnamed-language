package AST;
import Semantic.*;
import MyTypes.*;
import IR.*;

public class FunctionDecl {
	public MyType ctype;
	public Identifier id;
	public FormalParameter formalParams;

	public FunctionDecl(MyType ct, Identifier i, FormalParameter fp) {
		ctype = ct;
		id = i;
		formalParams = fp;
	}
	
	public void accept (Visitor v) {
		v.visit(this);
	}

	public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
	}

	public void accept(IRVisitor v) throws IRException {
		v.visit(this);
	}
}


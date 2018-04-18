package AST;
import Semantic.*;
import MyTypes.*;
import IR.*;

public class Function {

	public FunctionDecl funDecl;
	public FunctionBody funBody;
	public String name;
	public MyType returnType;

	public Function (FunctionDecl fd, FunctionBody fb) {
		funDecl = fd;
		funBody = fb;
		name = fd.id.value;
		returnType = fd.ctype;		 
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



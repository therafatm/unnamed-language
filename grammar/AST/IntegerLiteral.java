package AST;
import Semantic.*;
import MyTypes.MyType;
import IR.*;

public class IntegerLiteral extends Expression {
	
	public String value;
	public int lineNumber;
	public int offset;
	
	public IntegerLiteral(String s, int line, int o){
		value = s;
		lineNumber = line;
		offset = o;
	}

	public void accept(Visitor v){
		v.visit(this);	
	}

	public MyType accept (TypeVisitor v) throws SemanticException {
		return v.visit(this);
	}

	public Temporary accept (IRVisitor v) throws IRException {
		return v.visit(this);
	}
}

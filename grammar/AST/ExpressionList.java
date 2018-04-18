package AST;
import java.util.ArrayList;
import Semantic.*;
import IR.*;

public class ExpressionList {
	
	public ArrayList<Expression> expressionList;
	public Identifier fid;

	public ExpressionList(){
		expressionList = new ArrayList<Expression>();	
	}

	public void AddToList(Expression e){
		expressionList.add(e);
	}

	public void SetFunctionIdentifier(Identifier i){
		fid = i;
	}

	public void accept (Visitor v) {
		v.visit(this);
	}

	public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
	}

	public Temporary accept (IRVisitor v) throws IRException {
		return v.visit(this);
	}
}

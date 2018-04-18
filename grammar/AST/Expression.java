package AST;
// import java.util.Vector;

import Semantic.*;
import MyTypes.MyType;
import IR.*;

public abstract class Expression {
	// public Expression baseExpr;
	// public Vector<Expression> operandExpressions;
	
	// public Expression(){
	// 	operandExpressions = new Vector<Expression>();
	// }

	// public void SetBaseExpression(Expression e){
	// 	baseExpr = e;
	// }

	// public void AddOperandExpr(Expression e){
	// 	operandExpressions.addElement(e);	
	// }

	// public void accept(Visitor v){
	// 	v.visit(this);	
	// }
	public abstract void accept(Visitor v);
	public abstract MyType accept(TypeVisitor v) throws SemanticException;	
	public abstract Temporary accept (IRVisitor v) throws IRException;
}

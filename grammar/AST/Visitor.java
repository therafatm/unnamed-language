package AST;
import MyTypes.MyType;

public interface Visitor
{
	public void visit (AddSubtractExpression e);
	public void visit (ArrayAssignmentStatement a);
	public void visit (ArrayReference a);
	public void visit (Block b);
	public void visit (BooleanLiteral b);
	public void visit (CharLiteral b);
	public void visit (MyType c);
	public void visit (EqualityExpression s);
	public void visit (Expression e);
    public void visit (ExpressionList el);
    public void visit (ExpressionStatement es);
	public void visit (FloatLiteral f);	
	public void visit (FormalParameter p);
	public void visit (Function f);
	public void visit (FunctionBody f);
	public void visit (FunctionCall f);
	public void visit (FunctionDecl f);
	public void visit (IdAssignmentStatement i);
	public void visit (Identifier i);
	public void visit (IfElseStatement v);
	public void visit (IfStatement i);
    public void visit (IntegerLiteral i);
    public void visit (LessThanExpression i);
	public void visit (FunctionParameter ce);
	public void visit (MultiplyExpression e);
	public void visit (ParenExpression p);
	public void visit (PrintLnStatement s);
	public void visit (PrintStatement s);	
	public void visit (Program p);
    public void visit (ReturnStatement s);
    public void visit (SemiColonStatement i);
	public void visit (StringLiteral e);
	public void visit (VariableDeclaration v);
	public void visit (WhileStatement s);
}
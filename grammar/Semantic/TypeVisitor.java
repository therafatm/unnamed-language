package Semantic;
import AST.*;
import MyTypes.MyType;

public interface TypeVisitor 
{
    public void visit (Program p) throws SemanticException;
    public void visit (Function f) throws SemanticException;
    public void visit (FunctionDecl fd) throws SemanticException;
    public void visit (FunctionBody fb) throws SemanticException;
    public MyType visit (MyType c) throws SemanticException;
    public void visit (FormalParameter fp) throws SemanticException;
    // public void visit (FunctionParameter fp) throws SemanticException;
    public void visit (VariableDeclaration vd) throws SemanticException;
    public void visit (Block b) throws SemanticException;
    public void visit (Statement s) throws SemanticException;
    public void visit (IfStatement is) throws SemanticException;
    public void visit (IfElseStatement ies) throws SemanticException;
    public void visit (WhileStatement s) throws SemanticException;
    public void visit (ReturnStatement rs) throws SemanticException;
    public void visit (PrintLnStatement pls) throws SemanticException;
    public void visit (PrintStatement ps) throws SemanticException;
    public void visit (SemiColonStatement i);
    public void visit (ExpressionStatement es) throws SemanticException;
    public void visit (ArrayAssignmentStatement aa) throws SemanticException;
    public void visit (IdAssignmentStatement ida) throws SemanticException;
    public MyType visit (Expression e);
    public MyType visit (FunctionCall fc) throws SemanticException;
    public void visit (ExpressionList el) throws SemanticException;
    public MyType visit (AddSubtractExpression asExp) throws SemanticException;
    public MyType visit (BooleanLiteral b);
    public MyType visit (ArrayReference a) throws SemanticException;
    public MyType visit (CharLiteral charLit);
    public MyType visit (Identifier i) throws SemanticException;
    public MyType visit (EqualityExpression eqExp) throws SemanticException;
    public MyType visit (FloatLiteral f);
    public MyType visit (IntegerLiteral i);
    public MyType visit (StringLiteral s);
    public MyType visit (LessThanExpression lte) throws SemanticException;
    public MyType visit (MultiplyExpression multExp) throws SemanticException;
    public MyType visit (ParenExpression p) throws SemanticException;
}
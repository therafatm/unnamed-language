package IR;
import AST.*;

public interface IRVisitor 
{
    public void visit (Program p, String name) throws IRException ;
    public void visit (Function f) throws IRException ;
    public void visit (FunctionDecl fd) throws IRException ;
    public void visit (FunctionBody fb) throws IRException ;
    public void visit (FormalParameter fp) throws IRException ;
    public void visit (VariableDeclaration vd) throws IRException ;
    public void visit (Block b) throws IRException ;
    public void visit (Statement s) throws IRException ;
    public void visit (IfStatement is) throws IRException ;
    public void visit (IfElseStatement ies) throws IRException ;
    public void visit (WhileStatement s) throws IRException ;
    public void visit (ReturnStatement rs) throws IRException ;
    public void visit (PrintLnStatement pls) throws IRException ;
    public void visit (PrintStatement ps) throws IRException ;
    public void visit (SemiColonStatement i);
    public void visit (ExpressionStatement es) throws IRException ;
    public void visit (ArrayAssignmentStatement aa) throws IRException ;
    public void visit (IdAssignmentStatement ida) throws IRException ;
    public Temporary visit (Expression e);
    public Temporary visit (FunctionCall fc) throws IRException ;
    public Temporary visit (ExpressionList el) throws IRException ;
    public Temporary visit (AddSubtractExpression asExp) throws IRException ;
    public Temporary visit (BooleanLiteral b) throws IRException ;
    public Temporary visit (ArrayReference a) throws IRException ;
    public Temporary visit (CharLiteral charLit) throws IRException ;
    public Temporary visit (Identifier i) throws IRException ;
    public Temporary visit (EqualityExpression eqExp) throws IRException ;
    public Temporary visit (FloatLiteral f) throws IRException ;
    public Temporary visit (IntegerLiteral i) throws IRException ;
    public Temporary visit (StringLiteral s) throws IRException ;
    public Temporary visit (LessThanExpression lte) throws IRException ;
    public Temporary visit (MultiplyExpression multExp) throws IRException ;
    public Temporary visit (ParenExpression p) throws IRException ;
}
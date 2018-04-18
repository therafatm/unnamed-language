package AST;
import java.util.ArrayList;
import Semantic.*;
import IR.*;

public class FunctionBody {
    public ArrayList<VariableDeclaration> varDecls;
    public ArrayList<Statement> statements;

        public FunctionBody() {
                varDecls = new ArrayList<VariableDeclaration>();
                statements = new ArrayList<Statement>();
        }

        public void AddVarDecl (VariableDeclaration vd) {
                varDecls.add(vd);
        }

        public void AddStatement (Statement stmt) {
                statements.add(stmt);		
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

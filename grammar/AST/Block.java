package AST;
import java.util.ArrayList;
import Semantic.*;
import IR.*;

public class Block {

    public ArrayList<Statement> statements;

    public Block(){
   		statements = new ArrayList<Statement>(); 
	}
	
	public void AddStatement(Statement s){
		statements.add(s);
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

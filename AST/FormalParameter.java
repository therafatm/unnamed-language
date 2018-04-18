
package AST;
import java.util.HashMap;
import java.util.ArrayList;
import MyTypes.*;
import Semantic.*;
import IR.*;

public class FormalParameter {

	public ArrayList<FunctionParameter> parameterList;

	public FormalParameter() {
		parameterList = new ArrayList<FunctionParameter>();
	}
	
	public void AddMoreFormals(FunctionParameter fp){
		parameterList.add(fp);	
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


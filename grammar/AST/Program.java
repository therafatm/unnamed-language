package AST;
import java.util.ArrayList;
import Semantic.*;
import IR.*;

public class Program {

        public ArrayList<Function> functionList;

        public Program () {
                functionList = new ArrayList<Function>();
        }

        public void addElement (Function f) {
                functionList.add(f);
        }       

       public void accept (Visitor v) {
               v.visit(this);
        }
        
        public void accept (TypeVisitor v) throws SemanticException {
		v.visit(this);
        }
        
        public void accept(IRVisitor v, String name) throws IRException {
		v.visit(this, name);
	}
}

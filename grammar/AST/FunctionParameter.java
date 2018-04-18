package AST;
import MyTypes.MyType;
import Semantic.*;

public class FunctionParameter {

        public MyType ctype;
        public Identifier id;

        public FunctionParameter(MyType ct, Identifier i) {
                ctype = ct;
                id = i;
        }

	public void accept (Visitor v) {
		v.visit(this);
	}
}

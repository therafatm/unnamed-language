package MyTypes;

import MyTypes.MyType;
import MyTypes.floatType;

public class intType extends MyType {

	public intType (String s, int line, int o) {
		super(s,line,o);
	}

	public boolean isSubtypeOf (MyType type){
		if (type.equals(new floatType("float", 0, 0))){
			return true;
		}
		if (type.equals(new floatType("int", 0, 0))){
			return true;
		}
		return false;
	}

	public MyType accept () {
		return this;
	}
}


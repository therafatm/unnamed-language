package MyTypes;

public class floatType extends MyType {

	public floatType (String s, int line, int o) {
		super(s,line,o);
	}

	public String toString () {
		return t;
	}

	public boolean isSubtypeOf (MyType type){
		if (type.equals(new floatType("float", 0, 0))){
			return true;
		}
		return false;
	}
	
	public MyType accept () {
		return this;
	}
}


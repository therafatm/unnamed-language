package MyTypes;

public class voidType extends MyType {

	public voidType (String s, int line, int o) {
		super(s,line,o);
	}

	public String toString () {
		return t;
	}

	public MyType accept () {
		return this;
	}
}


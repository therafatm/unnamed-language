package MyTypes;

public class booleanType extends MyType {

	public booleanType (String s, int line, int o) {
		super(s,line,o);
	}

	public String toString () {
		return t;
	}

	public MyType accept () {
		return this;
	}
}


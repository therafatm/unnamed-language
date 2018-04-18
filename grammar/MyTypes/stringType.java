package MyTypes;

public class stringType extends MyType {

	public stringType (String s, int line, int o) {
		super(s,line,o);
	}

	public String toString () {
		return t;
	}

	public MyType accept () {
		return this;
	}
}


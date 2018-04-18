package MyTypes;

public class charType extends MyType {

	public charType (String s, int line, int o) {
		super(s,line,o);
	}

	public String toString () {
		return t;
	}

	public MyType accept () {
		return this;
	}
}


package AST;

import Semantic.*;
import IR.*;

public abstract class Statement {
    public abstract void accept(Visitor v);
    public abstract void accept(TypeVisitor v) throws SemanticException;
    public abstract void accept (IRVisitor v) throws IRException;
}


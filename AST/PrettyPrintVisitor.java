package AST;

import java.util.ArrayList;
import java.util.Iterator;
import MyTypes.*;

public class PrettyPrintVisitor implements Visitor {

    public String tab = "    ";
    public String tabBuff = "    ";

    public void visit (Program p){
        Iterator<Function> fit = p.functionList.iterator();
        while(fit.hasNext()){
            Function f = fit.next();
            f.accept(this);
            if(fit.hasNext()){
                System.out.print("\n");
            }
        }
    };

    public void visit (Function f){
        f.funDecl.accept(this);
        f.funBody.accept(this);
    };

    public void visit (FunctionDecl fd){
        fd.ctype.accept(this);
        fd.id.accept(this);
        System.out.print(" ");
        fd.formalParams.accept(this);
    }

    public void visit (FunctionBody fb){
        System.out.print("{");
        
        Iterator<VariableDeclaration> vdit = fb.varDecls.iterator();
        while(vdit.hasNext()){
            System.out.print("\n" + tab);
            VariableDeclaration vd = vdit.next();
            vd.accept(this);
        }

        if(fb.varDecls.size() > 0){
            System.out.print("\n");
        }

        Iterator<Statement> sit = fb.statements.iterator();
        while(sit.hasNext()){
            System.out.print("\n" + tab);
            Statement s = sit.next();
            s.accept(this);
        }
        
        System.out.println("\n}");
    }

    public void visit (MyType c){
        System.out.print(c.t.toString());
        if (c.isArray){
            System.out.print("[" + c.arraySize + "]");
        }
        System.out.print(" ");
    }

    public void visit (Identifier i){
        System.out.print(i.value);
    }

    public void visit (FormalParameter fp){
        System.out.print("(");

        for (int i = 0; i < fp.parameterList.size() ; i++){
            fp.parameterList.get(i).accept(this);
            if (i + 1 < fp.parameterList.size()) {
                System.out.print(", ");    
            }
        }

        System.out.println(")");
    }

    public void visit (FunctionParameter fp){
        fp.ctype.accept(this);
        fp.id.accept(this);
    }

    public void visit (VariableDeclaration vd){
        vd.ctype.accept(this);
        vd.id.accept(this);
        System.out.print(";"); 
    }

    public void visit (Block b){
        //increase indent
        tab = tab + tabBuff;
        //do block
        Iterator<Statement> sit = b.statements.iterator();
        while(sit.hasNext()){
            // indent
            System.out.print("\n" + tab);
            Statement s = sit.next();
            s.accept(this);
        }

        int cutoff = tab.length() - tabBuff.length();
        //decrease indent
        tab = tab.substring(0, cutoff);
    }

    public void visit (IfStatement is){
        // indent
        System.out.print("if (");
        is.expr.accept(this);
        System.out.print(")\n");

        //open curly brackets
        System.out.print(tab + "{");
        is.b1.accept(this);
        //close curly brackets
        System.out.print("\n" + tab + "}");
    }

    public void visit (IfElseStatement ies){
        // indent
        System.out.print("if (");
        ies.expr.accept(this);
        System.out.print(")\n");

        //open curly brackets
        System.out.print(tab + "{");
        ies.b1.accept(this);
        //close curly brackets
        System.out.print("\n" + tab + "}");

        System.out.print("\n" + tab + "else\n");
        
        //open curly brackets
        System.out.print(tab + "{");
        ies.b2.accept(this);
        //close curly brackets
        System.out.print("\n" + tab + "}");
    }

    public void visit (WhileStatement s){
        System.out.print("while (");
        s.expr.accept(this);
        System.out.print(")\n");
       
        //open curly brackets
        System.out.print(tab + "{");
        s.b.accept(this);
        System.out.print("\n" + tab + "}");
    }

    public void visit (ReturnStatement rs){
        System.out.print("return ");
        if (rs.expr != null){
            rs.expr.accept(this);
        }
        System.out.print(";");
    }

    public void visit (PrintLnStatement pls){
        System.out.print("println ");
        pls.expr.accept(this);
        System.out.print(";");
    }

    public void visit (PrintStatement ps){
        System.out.print("print ");
        ps.expr.accept(this);
        System.out.print(";");
    }

    public void visit (SemiColonStatement i){
        System.out.print(i.value);
    }

    public void visit (ExpressionStatement es){
        es.expr.accept(this);
        System.out.print(";");
    }

    public void visit (ArrayAssignmentStatement aa){
        aa.id.accept(this);

        System.out.print("[");
        aa.e1.accept(this);
        System.out.print("]=");

        aa.e2.accept(this);
        System.out.print(";");
    }

    public void visit (IdAssignmentStatement ida){
        ida.id.accept(this);
        System.out.print("=");
        ida.expr.accept(this);
        System.out.print(";");
    }

    public void visit (Expression e){
        return;
    }

    public void visit (FunctionCall fc){
        fc.id.accept(this);
        System.out.print("(");
        fc.exprList.accept(this);
        System.out.print(")");
    }

    public void visit (ExpressionList el){
        if (el.expressionList.size() == 0){
            return;
        }
        
        Iterator<Expression> eit = el.expressionList.iterator();
        while(eit.hasNext()){
            Expression inner = eit.next();
            inner.accept(this);
            if(eit.hasNext()){
                System.out.print(",");
            }
        }
    }

    public void visit (AddSubtractExpression asExp){
        asExp.left.accept(this);
        System.out.print(asExp.operand);
        asExp.right.accept(this);
    }

    public void visit (BooleanLiteral b){
        System.out.print(b.value);
    }

    public void visit (ArrayReference a){
        a.id.accept(this);
        System.out.print("[");
        a.expr.accept(this);
        System.out.print("]");
    }

    public void visit (CharLiteral charLit){
        System.out.print(charLit.value);
    }

    public void visit (EqualityExpression eqExp){
        eqExp.left.accept(this);
        System.out.print(eqExp.operand);
        eqExp.right.accept(this);
    }

    public void visit (FloatLiteral f){
        System.out.print(f.value);
    }

    public void visit (IntegerLiteral i){
        System.out.print(i.value);
    }

    public void visit (StringLiteral s){
        System.out.print(s.value);
    }

    public void visit (LessThanExpression lte){
        lte.left.accept(this);
        System.out.print(lte.operand);
        lte.right.accept(this);
    }

    public void visit (MultiplyExpression multExp){
        multExp.left.accept(this);
        System.out.print(multExp.operand);
        multExp.right.accept(this);
    }

    public void visit (ParenExpression p){
        System.out.print("(");
        p.expr.accept(this);
        System.out.print(")");
    }
}
package Semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.lang.Boolean;

import AST.*;
import MyTypes.*;

public class TypeCheckVisitor implements TypeVisitor {

    public HashMap<String, FunctionEnvironment> functionEnvironments = new HashMap<String, FunctionEnvironment>();
    public FunctionEnvironment currentFunctionEnvironment = null;
    public boolean isAssignmentStatement = false;

    public void visit (Program p) throws SemanticException {

        int mainCount = 0;
        Function mainFunc = null;
        boolean mainFuncType = false;
        boolean validMainFuncArgNum = true;

        // To check for global function uniqueness
        HashMap<String, Boolean> uniqueFuncs = new HashMap<String, Boolean>();

        //Do program level checks
        if (p.functionList.size() < 1) {
            String msg = "Program does not contain any valid functions";
            throw new SemanticException(msg, 0, 0);
        }
        
        Iterator<Function> fit = p.functionList.iterator();
        while(fit.hasNext()){
            Function f = fit.next();
            if (f.name.equals("main")) {
                mainCount++;
                mainFuncType = f.funDecl.ctype.t.equals("void");

                // check main function type
                if (!mainFuncType){
                    String msg = "Main function does not have correct void type.";
                    int line = f.funDecl.id.lineNumber;
                    int offset = 0;
                    throw new SemanticException(msg, line, offset);        
                }
                // check main function parameters
                if (f.funDecl.formalParams.parameterList.size() > 0){
                    String msg = "Main function has arguments";
                    int line = f.funDecl.id.lineNumber;
                    int offset = f.funDecl.id.offset;
                    throw new SemanticException(msg, line, offset);    
                }
            }

            if (mainCount > 1) {
                String msg = "Program contains multiple main functions.";
                int line = f.funDecl.id.lineNumber;
                int offset = f.funDecl.id.offset;
                throw new SemanticException(msg, line, offset);
            }

            if (uniqueFuncs.get(f.name) != null){
                String msg = "Program contains multiple functions called: " + f.name;
                int line = f.funDecl.id.lineNumber;
                int offset = f.funDecl.id.offset;
                throw new SemanticException(msg, line, offset);                
            } else {
                uniqueFuncs.put(f.name, true);
                FunctionEnvironment env = new FunctionEnvironment(f.name, f.returnType);
                functionEnvironments.put(f.name, env);
                currentFunctionEnvironment = env;
                // visit function signature to store
                // in function environment
                f.funDecl.accept(this);
            }
        }

        if (mainCount < 1) {
            String msg = "Program does not contain a main function.";
            throw new SemanticException(msg, 0, 0);
        }

        // Visit the functions
        fit = p.functionList.iterator();
        while(fit.hasNext()){
            Function f = fit.next();
            f.accept(this);
        }
    }

    public void visit (Function f) throws SemanticException {
        currentFunctionEnvironment = functionEnvironments.get(f.name);
        // f.funDecl.accept(this);
        f.funBody.accept(this);
    }

    public void visit (FunctionDecl fd) throws SemanticException {
        fd.formalParams.accept(this);
    }

    public void visit (FormalParameter fp) throws SemanticException {
        HashMap<String, Boolean> uniqueParams = new HashMap<String, Boolean>();

        for (int i = 0; i < fp.parameterList.size() ; i++){
             FunctionParameter param = fp.parameterList.get(i);
 
            if (param.ctype.t.equals("void")){
                String msg = "Parameter " + param.id.value + " has type void";
                throw new SemanticException(msg, param.id.lineNumber, param.id.offset);
            }

            if(uniqueParams.get(param.id.value) != null){
                String msg = "Two function parameters have the same name.";
                throw new SemanticException(msg, param.id.lineNumber, param.id.offset); 
            }

            uniqueParams.put(param.id.value, true);
            currentFunctionEnvironment.parameters.put(param.id.value, param.ctype);
        }
    }

    public void visit (FunctionBody fb) throws SemanticException {
        HashMap<String, Boolean> uniqueVars = new HashMap<String, Boolean>();
        Iterator<VariableDeclaration> vdit = fb.varDecls.iterator();
        
        while(vdit.hasNext()){
            VariableDeclaration vd = vdit.next();
            if(uniqueVars.get(vd.id.value) != null){
                String msg = "Two local variables within the function have the same name";
                throw new SemanticException(msg, vd.id.lineNumber, vd.id.offset);
            }

            if(currentFunctionEnvironment.parameters.get(vd.id.value) != null){
                String msg = "Local variables are shadowing function parameters.";
                throw new SemanticException(msg, vd.id.lineNumber, vd.id.offset); 
            }

            if(vd.ctype.t.equals("void")){
                String msg = "Local variable has type void.";
                throw new SemanticException(msg, vd.id.lineNumber, vd.id.offset); 
            }

            uniqueVars.put(vd.id.value, true);
            vd.accept(this);
        }

        Iterator<Statement> sit = fb.statements.iterator();
        while(sit.hasNext()){
            Statement s = sit.next();
            s.accept(this);
        }

        // if (!currentFunctionEnvironment.hasReturnStatement){
        //     if(currentFunctionEnvironment.returnType.t.equals("void")){
        //         return;
        //     } else {
        //         String msg = "Current function has a return type of " + currentFunctionEnvironment.returnType + " ";
        //         msg += "but no return statement was found.";
        //         MyType ftype = currentFunctionEnvironment.returnType;
        //         throw new SemanticException(msg, ftype.lineNumber, ftype.offset);     
        //     }
        // }
    }

    public void visit (Statement c) throws SemanticException {
        return;
    }

    public MyType visit (MyType c) throws SemanticException {
        return c;
    }

    public void visit (VariableDeclaration vd) throws SemanticException {
        VariableTuple vt = new VariableTuple(vd.id, vd.ctype);
        currentFunctionEnvironment.variables.put(vd.id.value, vt);
    }

    public void visit (Block b) throws SemanticException {
        Iterator<Statement> sit = b.statements.iterator();
        while(sit.hasNext()){
            Statement s = sit.next();
            s.accept(this);
        }
    }

    public void visit (IfStatement is) throws SemanticException {
        MyType conditionType = is.expr.accept(this);
        if (!conditionType.equals(new booleanType("boolean", 0, 0))){
            String msg = "Condition within if block is not a bolean value.";
            throw new SemanticException(msg, conditionType.lineNumber, conditionType.offset);
        }

        is.b1.accept(this);
    }

    public void visit (IfElseStatement ies) throws SemanticException {
        MyType conditionType = ies.expr.accept(this);
        if (!conditionType.equals(new booleanType("boolean", 0, 0))){
            String msg = "Condition within if block is not a bolean value.";
            throw new SemanticException(msg,conditionType.lineNumber, conditionType.offset);
        }
        
        ies.b1.accept(this);
        ies.b2.accept(this);
    }

    public void visit (WhileStatement s) throws SemanticException {
        MyType conditionType = s.expr.accept(this);
        if (!conditionType.equals(new booleanType("boolean", 0, 0))){
            String msg = "Condition within if block is not a bolean value.";
            throw new SemanticException(msg,conditionType.lineNumber, conditionType.offset);
        }

        s.b.accept(this);
    }

    public void visit (ReturnStatement rs) throws SemanticException {
        if (rs.expr == null && currentFunctionEnvironment.returnType.equals(new voidType("void",0,0))){
            currentFunctionEnvironment.hasReturnStatement = true;
            return;
        }

        MyType returnType = rs.expr.accept(this);
        boolean isSubType = returnType.isSubtypeOf(currentFunctionEnvironment.returnType);
        if (!currentFunctionEnvironment.returnType.equals(returnType) && !isSubType){
            String msg = "Return value of type " + currentFunctionEnvironment.returnType.toString() + " does not match expected return type of " + returnType.toString() + ".";
            throw new SemanticException(msg, rs.lineNumber, rs.offset);
        }

        currentFunctionEnvironment.hasReturnStatement = true;
        return;
    }

    public void visit (PrintLnStatement pls) throws SemanticException {
        MyType etype = pls.expr.accept(this);
        if (etype.t.equals("void")){
            String msg = "Expression within print statement evaluates to void.";
            throw new SemanticException(msg, etype.lineNumber, etype.offset);
        }
        return;
    }

    public void visit (PrintStatement ps) throws SemanticException {
        MyType etype = ps.expr.accept(this);
        if (etype.t.equals("void")){
            String msg = "Expression within print statement evaluates to void.";
            throw new SemanticException(msg, etype.lineNumber, etype.offset);
        }
        return;
    }

    public void visit (SemiColonStatement i){
    }

    public void visit (ExpressionStatement es) throws SemanticException{
        es.expr.accept(this);
    }

    public void visit (ArrayAssignmentStatement aa) throws SemanticException {
        // isAssignmentStatement = true;
        MyType arrayType = aa.id.accept(this);
        // isAssignmentStatement = false;

        MyType arrayIndexType = aa.e1.accept(this);
        if (!arrayIndexType.equals(new intType("int", 0, 0))){
            String msg = "Array index type " + arrayIndexType.toString() + " does not match with expected type int"; 
            throw new SemanticException(msg, arrayIndexType.lineNumber, arrayIndexType.offset); 
        }

        MyType arrayBaseType = new MyType(arrayType.t, 0, 0);        
        MyType valueType = aa.e2.accept(this);
        if (!arrayBaseType.equals(valueType)){
            String msg = "Assigned value type " + valueType.toString() + " does not match with declared array type " + arrayType.toString();
            throw new SemanticException(msg, aa.id.lineNumber, aa.id.offset);
        }

        // set local variable to be defined
        // VariableTuple nvt = new VariableTuple(aa.id, arrayType);
        // nvt.setIsDefined();
        // currentFunctionEnvironment.variables.put(aa.id.value, nvt);
    }

    public void visit (IdAssignmentStatement ida) throws SemanticException {
        // isAssignmentStatement = true;
        MyType variableType = ida.id.accept(this);
        // isAssignmentStatement = false;
        MyType valueType = ida.expr.accept(this);

        if (!variableType.equals(valueType)){
            // saving int value in float variable
            if (valueType.isSubtypeOf(variableType)){
                // set local variable to be defined
                // VariableTuple nvt = new VariableTuple(ida.id, variableType);
                // nvt.setIsDefined();
                // currentFunctionEnvironment.variables.put(ida.id.value, nvt);
                return;
            }

            String msg = "Assigned value type " + valueType.toString() + " does not match with declared variable type " + variableType.toString();
            throw new SemanticException(msg, ida.id.lineNumber, ida.id.offset);
        }

        // set local variable to be defined
        // VariableTuple nvt = new VariableTuple(ida.id, variableType);
        // nvt.setIsDefined();
        // currentFunctionEnvironment.variables.put(ida.id.value, nvt);
    }

    public MyType visit (Expression e){
        return new voidType("void", 0, 0);
    }

    public MyType visit (FunctionCall fc) throws SemanticException {
        FunctionEnvironment fenv = functionEnvironments.get(fc.id.value);
        if (fenv == null){
            String msg = "Function " + fc.id.value + " does not exist";
            throw new SemanticException(msg, fc.id.lineNumber, fc.id.offset);
        }

        if (fc.exprList.expressionList.size() != fenv.parameters.size()){
            String msg = "Call to function " + fc.id.value + " has inconsistent number of parameters compared to function signature.";
            throw new SemanticException(msg, fc.id.lineNumber, fc.id.offset); 
        }

        fc.exprList.accept(this);
        return fenv.returnType;
    }

    public void visit (ExpressionList el) throws SemanticException {
        FunctionEnvironment fenv = functionEnvironments.get(el.fid.value);
        Iterator<MyType> paramit = fenv.parameters.values().iterator();
        Iterator<Expression> elit = el.expressionList.iterator();

        while(paramit.hasNext()){
            MyType param = paramit.next();
            Expression e = elit.next(); 
            MyType eType = e.accept(this);

            boolean isSubType = eType.isSubtypeOf(param);
            if (!param.equals(eType) && !isSubType){
                String msg = "Incosistent parameter types. Expected " + param.toString() + ", but given " + eType.toString() + ".";
                throw new SemanticException(msg, eType.lineNumber, eType.offset);  
            }
        }
    }

    public MyType visit (AddSubtractExpression asExp) throws SemanticException {
        MyType lhs = asExp.left.accept(this);
        MyType rhs = asExp.right.accept(this);
        String msg = "Mismatched types " + lhs.toString() + " and " + rhs.toString() + " when executing " + asExp.operand + " operation.";

        if (lhs.t.equals("void") || lhs.t.equals("boolean") || (lhs.t.equals("string") && asExp.operand.equals("-"))){
            throw new SemanticException(msg, asExp.lineNumber, asExp.offset);
        }

        if (lhs.equals(rhs) && !lhs.isArray && !rhs.isArray){ 
            return lhs;
        } else {
            boolean isSubtype = lhs.isSubtypeOf(rhs) || rhs.isSubtypeOf(lhs);
            if (isSubtype){
                return lhs.isSubtypeOf(rhs) ? rhs : lhs;
            }
            throw new SemanticException(msg, asExp.lineNumber, asExp.offset);
        }
    }

    public MyType visit (MultiplyExpression multExp) throws SemanticException {
        MyType lhs = multExp.left.accept(this);
        MyType rhs = multExp.right.accept(this);
        
        String msg = "Mismatched types " + lhs.toString() + " and " + rhs.toString() + " when executing multiplication operation.";
        
        if ((lhs.t.equals("int") || lhs.t.equals("float")) && lhs.equals(rhs)){
            return lhs;
        } else {
            boolean isSubtype = lhs.isSubtypeOf(rhs) || rhs.isSubtypeOf(lhs);
            if (isSubtype){
                return lhs.isSubtypeOf(rhs) ? rhs : lhs;
            } 
            throw new SemanticException(msg, multExp.lineNumber, multExp.offset);
        }
    }

    public MyType visit (LessThanExpression lte) throws SemanticException {
        MyType lhs = lte.left.accept(this);
        MyType rhs = lte.right.accept(this);

        String msg = "Mismatched types " + lhs.toString() + " and " + rhs.toString() + " when executing " + lte.operand + " operation.";

        if (lhs.t.equals("void")){
            throw new SemanticException(msg, lte.lineNumber, lte.offset);
        }

        if (lhs.equals(rhs)){
            return new booleanType("boolean", lhs.lineNumber , lhs.offset);
        } else {
            boolean isSubtype = rhs.isSubtypeOf(lhs);
            if (isSubtype){
                return new booleanType("boolean", lhs.lineNumber , lhs.offset);
            }
            throw new SemanticException(msg, lte.lineNumber, lte.offset);
        }
    }

    public MyType visit (EqualityExpression eqExp) throws SemanticException {
        MyType lhs = eqExp.left.accept(this);
        MyType rhs = eqExp.right.accept(this);
        
        String msg = "Mismatched types " + lhs.toString() + " and " + rhs.toString() + " when executing " + eqExp.operand + " operation.";

        if (!lhs.t.equals("void") && lhs.equals(rhs)){
            return new booleanType("boolean", lhs.lineNumber , lhs.offset);
        } else {
            boolean isSubtype = lhs.isSubtypeOf(rhs) || rhs.isSubtypeOf(lhs);
            if (isSubtype){
                return new booleanType("boolean", lhs.lineNumber , lhs.offset);
            }
            throw new SemanticException(msg, eqExp.lineNumber, eqExp.offset);
        }
    }

    public MyType visit (ParenExpression p) throws SemanticException {
        return p.expr.accept(this);
    }

    public MyType visit (BooleanLiteral b){
        return new booleanType("boolean", b.lineNumber, b.offset);
    }

    public MyType visit (ArrayReference a) throws SemanticException {
        MyType arrayType = a.id.accept(this);
        MyType arrayIndexType = a.expr.accept(this);
        if (!arrayIndexType.equals(new intType("int", 0, 0))){
            String msg = "Array index type is expected to be int, but was given " + arrayIndexType.toString(); 
            throw new SemanticException(msg, a.lineNumber, a.offset);
        }

        return new MyType(arrayType.t, arrayType.lineNumber, arrayType.offset);
    }

    public MyType visit (CharLiteral charLit){
        return new charType("char", charLit.lineNumber, charLit.offset);
    }

    public MyType visit (Identifier i) throws SemanticException {
        MyType variableType = currentFunctionEnvironment.parameters.get(i.value);
        if (variableType == null) {
            VariableTuple vt = currentFunctionEnvironment.variables.get(i.value);
            if (vt != null){
                // if (!isAssignmentStatement && !vt.isDefined){
                //     String msg = "Variable " + i.value + " has not been defined in function scope.";
                //     throw new SemanticException(msg, i.lineNumber, i.offset);
                // }
                variableType = vt.returnType;
            }
        }

        if (variableType == null) {
            String msg = "Variable " + i.value + " does not exist in function scope.";
            throw new SemanticException(msg, i.lineNumber, i.offset);
        }
        
        return variableType;
    }

    public MyType visit (FloatLiteral f){
        return new floatType("float", f.lineNumber, f.offset);
    }

    public MyType visit (IntegerLiteral i){
        return new intType("int", i.lineNumber, i.offset);
    }

    public MyType visit (StringLiteral s){
        return new stringType("string", s.lineNumber, s.offset);
    }
}
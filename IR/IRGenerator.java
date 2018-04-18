package IR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.awt.print.Printable;
import java.lang.Boolean;

import AST.*;
import MyTypes.*;
import Semantic.*;
import IR.*;

public class IRGenerator implements IRVisitor {

    public HashMap<String, IRFunctionEnvironment> functionEnvironments = new HashMap<String, IRFunctionEnvironment>();
    public IRFunctionEnvironment currentFunctionEnvironment = null;
    public TempManager currentTempManager = null;
    public int rootLabelCount = 0;
    public int irOffset = 1;
    public String pname = "";

    public String getLabelID() {
        rootLabelCount++;
        return String.valueOf(rootLabelCount - 1);
    }

    public int getCurrentIRLine(int irIndex) {
        // +2 for function header and {
        // +1 for 0 offset
        return irOffset + 2 + currentTempManager.orderedTempInit.size() + irIndex + 1;
    }

    public void visit (Program p, String name) throws IRException  {
        System.out.println(".source " + name + ".ir");
        System.out.println(".class public " + name);
        System.out.println(".super java/lang/Object\n");

        this.pname = name;
        irOffset++;

        Function mainFunc = null;
        Iterator<Function> fit = p.functionList.iterator();
        while(fit.hasNext()){
            Function f = fit.next();
            IRFunctionEnvironment env = new IRFunctionEnvironment(f.name, f.returnType);
            functionEnvironments.put(f.name, env);
            currentFunctionEnvironment = env;
            currentTempManager = env.Hagrid;
            // visit function signature for symbol table
            f.funDecl.accept(this);
        }

        // Visit the functions
        fit = p.functionList.iterator();
        while(fit.hasNext()){
            Function f = fit.next();
            f.accept(this);
            System.out.print("\n");
        }

        System.out.println("\n;------boilerplate------;\n");
        System.out.println(".method public static main([Ljava/lang/String;)V");
        System.out.println("\t.limit locals 1");
        System.out.println("\t.limit stack 4");
        System.out.println("\tinvokestatic " + this.pname + "/__main()V");
        System.out.println("\treturn");
        System.out.println(".end method\n");

        System.out.println("; standard initializer");
        System.out.println(".method public <init>()V");
        System.out.println("\taload_0");
        System.out.println("\tinvokenonvirtual java/lang/Object/<init>()V");
        System.out.println("\treturn");
        System.out.println(".end method"); 
    }

    public void visit(Function f) throws IRException {
        currentTempManager.PushTemporaryBlock();
        int startLabel = rootLabelCount;

        currentFunctionEnvironment = functionEnvironments.get(f.name);
        currentTempManager = currentFunctionEnvironment.Hagrid;

        String initlabel = getLabelID();
        String endlabel = getLabelID();

        f.funBody.accept(this);

        // put return statement in the end to force termination
        currentFunctionEnvironment.IRInstructionList.add("RETURN;");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("return");

        // visit the IRlist for each function
        String fsig = f.name.equals("main") ? "__" + currentFunctionEnvironment.functionIRSignature
                : currentFunctionEnvironment.functionIRSignature;
        System.out.println(".method public static " + fsig);
        System.out.println("\t.limit locals " + currentTempManager.orderedTempInit.size());
        for (int i = 0; i < currentTempManager.orderedTempInit.size(); i++) {
            Temporary t = currentTempManager.orderedTempInit.get(i);
            String type = t.getType().contains("U") ? "Ljava/lang/String;" : t.getType();
            if (t.type.isArray) {
                if(type.startsWith("Ljava")){
                    type = "[" + type;
                } else {
                    type = "[" + type.substring(1);
                }
            }

            System.out.println( "\t.var " + i + " is T" + t.id + "  " + type + " from L_" + startLabel + " to L_" + endlabel);
        }
        System.out.println("\t.limit stack 35");

        // print label
        System.out.println("L_" + initlabel + ":");
        // print ASM declarations
        String ir;
        String tab = "\t";
        for (int i = currentFunctionEnvironment.paramOffset; i < currentTempManager.orderedTempInit.size(); i++) {
            Temporary t = currentTempManager.orderedTempInit.get(i);
            t.printASMInit(tab);
        }

        System.out.println("");
        int IRlistIndex = 0;

        for (int i = 0; i < currentFunctionEnvironment.ASMInstructionList.size(); i++) {
            String s = currentFunctionEnvironment.ASMInstructionList.get(i);
            while (i < currentFunctionEnvironment.ASMInstructionList.size()) {
                s = currentFunctionEnvironment.ASMInstructionList.get(i);
                if (s.contains("instruction")) {
                    System.out.println(".line " + getCurrentIRLine(IRlistIndex));
                    System.out.println(";\t\t\t\t" + currentFunctionEnvironment.IRInstructionList.get(IRlistIndex));
                    break;
                } else {
                    s = currentFunctionEnvironment.ASMInstructionList.get(i);
                    if (s.startsWith("L")) {
                        System.out.println(s);
                    } else {
                        System.out.println("\t" + s);
                    }
                    i++;
                }
            }
            IRlistIndex++;
        }

        System.out.println("L_" + endlabel + ":");
        System.out.println(".end method");
        irOffset = getCurrentIRLine(IRlistIndex) + 1;

        currentTempManager.PopTemporaryBlock();
    }

    public void visit(FunctionDecl fd) throws IRException {
        fd.formalParams.accept(this);
    }

    public void visit(FormalParameter fp) throws IRException {

        // for variable ASM declaration
        currentFunctionEnvironment.paramOffset = fp.parameterList.size();

        String instruction = currentFunctionEnvironment.functionName + "(";
        String paramType;
        for (int i = 0; i < fp.parameterList.size(); i++) {
            FunctionParameter param = fp.parameterList.get(i);
            // add to environment parameters
            currentFunctionEnvironment.parameters.put(param.id.value, param.ctype);
            
            paramType = TypeConverter.convertType(param.ctype);
            if (param.ctype.isArray) {
                paramType = "[" + paramType.substring(1);
            }

            instruction += paramType;

            // add to param to temp map
            Temporary t = currentTempManager.addTemporary(param.ctype);
            currentFunctionEnvironment.paramsToTemp.put(param.id.value, t);
        }

        instruction += ")";
        paramType = TypeConverter.convertType(currentFunctionEnvironment.returnType).replace("A", "[");
        instruction += paramType;
        currentFunctionEnvironment.functionIRSignature = instruction;
    }

    public void visit(FunctionBody fb) throws IRException {

        Iterator<VariableDeclaration> vdit = fb.varDecls.iterator();
        while (vdit.hasNext()) {
            VariableDeclaration vd = vdit.next();
            vd.accept(this);
        }

        Iterator<Statement> sit = fb.statements.iterator();
        while (sit.hasNext()) {
            Statement s = sit.next();
            s.accept(this);
        }

    }

    public void visit(Statement c) throws IRException {
        return;
    }

    public void visit(VariableDeclaration vd) throws IRException {
        VariableTuple vt = new VariableTuple(vd.id, vd.ctype);
        currentFunctionEnvironment.variables.put(vd.id.value, vt);

        Temporary t = currentTempManager.addTemporary(vd.ctype);
        if (vd.ctype.isArray) {
            MyType arrayBaseType = new MyType(vd.ctype.t, 0, 0);
            String ir = t + " := NEWARRAY " + TypeConverter.convertType(arrayBaseType) + " " + vd.ctype.arraySize + ";";
            currentFunctionEnvironment.IRInstructionList.add(ir);
            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

            // push array size to stack
            String load_asm = "ldc " + vd.ctype.arraySize;
            currentFunctionEnvironment.ASMInstructionList.add(load_asm);
            // make new array?
            
            load_asm = vd.ctype.t.equals("string") ? "anewarray java/lang/String"  : "newarray " + arrayBaseType.t;
            currentFunctionEnvironment.ASMInstructionList.add(load_asm);
            // store in array reference
            load_asm = "astore " + t.id;
            currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        }

        currentFunctionEnvironment.varsToTemp.put(vd.id.value, t);
        // String varDecl = "TEMP " + t.id + ":" + t.getType() + "\t [L(\"" + vd.id.value + "\")];";
        // currentFunctionEnvironment.IRDeclarations.add(varDecl);
    }

    public void visit(Block b) throws IRException {
        Iterator<Statement> sit = b.statements.iterator();
        while (sit.hasNext()) {
            Statement s = sit.next();
            s.accept(this);
        }
    }

    public void visit(IfStatement is) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary t1 = is.expr.accept(this);
        String ir = t1 + " := Z! " + t1 + ";";
        String labelID = currentTempManager.getLabelID();
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(t1.getLoadInstruction() + " " + t1.id);
        currentFunctionEnvironment.ASMInstructionList.add("ldc 1");
        currentFunctionEnvironment.ASMInstructionList.add("ixor");
        currentFunctionEnvironment.ASMInstructionList.add("istore " + t1.id);

        ir = "IF " + t1 + " GOTO L" + labelID + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(t1.getLoadInstruction() + " " + t1.id);
        currentFunctionEnvironment.ASMInstructionList.add("ifne L" + labelID);

        // if block 
        is.b1.accept(this);
        // exit if block 
        currentFunctionEnvironment.IRInstructionList.add("GOTO L" + labelID + ";");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("goto L" + labelID);

        // start of code following if construct
        currentFunctionEnvironment.IRInstructionList.add("L" + labelID + ":;");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("L" + labelID + ":");

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public void visit(IfElseStatement ies) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary t1 = ies.expr.accept(this);
        String labelID = currentTempManager.getLabelID();
        String labelIDOut = currentTempManager.getLabelID();

        String ir = t1 + " := Z! " + t1 + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(t1.getLoadInstruction() + " " + t1.id);
        currentFunctionEnvironment.ASMInstructionList.add("ldc 1");
        currentFunctionEnvironment.ASMInstructionList.add("ixor");
        currentFunctionEnvironment.ASMInstructionList.add("istore " + t1.id);

        ir = "IF " + t1 + " GOTO L" + labelID + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(t1.getLoadInstruction() + " " + t1.id);
        currentFunctionEnvironment.ASMInstructionList.add("ifne L" + labelID);

        // if block
        ies.b1.accept(this);
        // exit if else construct
        currentFunctionEnvironment.IRInstructionList.add("GOTO L" + labelIDOut + ";");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("goto L" + labelIDOut);

        // else block
        currentFunctionEnvironment.IRInstructionList.add("L" + labelID + ":;");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("L" + labelID + ":");

        ies.b2.accept(this);
        // exit if else construct
        currentFunctionEnvironment.IRInstructionList.add("GOTO L" + labelIDOut + ";");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("goto L" + labelIDOut);

        // start of code following if else construct
        currentFunctionEnvironment.IRInstructionList.add("L" + labelIDOut + ":;");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("L" + labelIDOut + ":");

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public void visit(WhileStatement s) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        String labelIDin = currentTempManager.getLabelID();
        String labelIDout = currentTempManager.getLabelID();
        currentFunctionEnvironment.IRInstructionList.add("L" + labelIDin + ":;");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("L" + labelIDin + ":");

        Temporary t1 = s.expr.accept(this);
        String ir = t1 + " := Z! " + t1 + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(t1.getLoadInstruction() + " " + t1.id);
        currentFunctionEnvironment.ASMInstructionList.add("ldc 1");
        currentFunctionEnvironment.ASMInstructionList.add("ixor");
        currentFunctionEnvironment.ASMInstructionList.add("istore " + t1.id);

        // while expression
        ir = "IF " + t1 + " GOTO L" + labelIDout + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(t1.getLoadInstruction() + " " + t1.id);
        currentFunctionEnvironment.ASMInstructionList.add("ifne L" + labelIDout);

        // while block
        s.b.accept(this);
        // jump to beginning of while
        currentFunctionEnvironment.IRInstructionList.add("GOTO L" + labelIDin + ";");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("goto L" + labelIDin);

        // exit while construct 
        currentFunctionEnvironment.IRInstructionList.add("L" + labelIDout + ":;");
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add("L" + labelIDout + ":");

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public void visit(ReturnStatement rs) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();
        String ir;

        if (rs.expr == null && currentFunctionEnvironment.returnType.equals(new voidType("void", 0, 0))) {
            currentFunctionEnvironment.hasReturnStatement = true;
            ir = "RETURN;";
            currentFunctionEnvironment.IRInstructionList.add(ir);
            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            currentFunctionEnvironment.ASMInstructionList.add("return");
            return;
        }

        Temporary t = rs.expr.accept(this);
        MyType funcReturnType = currentFunctionEnvironment.returnType;
        boolean isSubtype = t.type.isSubtypeOf(funcReturnType) && !t.type.equals(funcReturnType);
        if (isSubtype) {
            Temporary t2 = currentTempManager.getAvailableTemporary(funcReturnType);
            ir = t2 + " := " + t.getType() + "2" + t2.getType() + " " + t + ";";
            currentFunctionEnvironment.IRInstructionList.add(ir);

            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            String temp_asm = t.getLoadInstruction() + " " + t.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = t.getType().toLowerCase() + "2" + t2.getType().toLowerCase();
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = t.getStoreInstruction() + " " + t.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

            t = t2;
        }

        if (t.indexTempId >= 0){
            t.justArray = true;
        }

        ir = "RETURN " + t + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        currentFunctionEnvironment.hasReturnStatement = true;

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        String load_asm = t.getLoadInstruction() + " " + t.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        load_asm = t.getReturnInstruction();
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return;
    }

    public void visit(PrintLnStatement pls) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary t = pls.expr.accept(this);
        String ir = "PRINTLN" + t.getType() + " " + t + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        String load_asm = "getstatic java/lang/System/out Ljava/io/PrintStream;";
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        load_asm = t.getLoadInstruction() + " " + t.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        String arg = "";
        if (t.getType().equals("U")) {
            arg = "Ljava/lang/String;";
        } else {
            arg = t.getType();
        }

        load_asm = "invokevirtual java/io/PrintStream/println(" + arg + ")V";
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public void visit(PrintStatement ps) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary t = ps.expr.accept(this);
        String ir = "PRINT" + t.getType() + " " + t + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        String load_asm = "getstatic java/lang/System/out Ljava/io/PrintStream;";
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        load_asm = t.getLoadInstruction() + " " + t.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        String arg = "";
        if (t.getType().equals("U")) {
            arg = "Ljava/lang/String;";
        } else {
            arg = t.getType();
        }

        load_asm = "invokevirtual java/io/PrintStream/print(" + arg + ")V";
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public void visit(SemiColonStatement i) {
    }

    public void visit(ExpressionStatement es) throws IRException {
        es.expr.accept(this);
    }

    public void visit(ArrayAssignmentStatement aa) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary array = aa.id.accept(this);
        Temporary index = aa.e1.accept(this);
        array.indexTempId = index.id;

        Temporary value = aa.e2.accept(this);

        String ir = array + " := " + value + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        // add marker for ir line in asm instruction
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        // push array reference on stack
        String load_asm = "aload " + array.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        // push array index on stack
        load_asm = "iload " + index.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        // push value to store on stack
        load_asm = value.getLoadInstruction() + " " + value.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        // store value in array
        currentFunctionEnvironment.ASMInstructionList.add(array.getArrayStoreInstruction());

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public void visit(IdAssignmentStatement ida) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary t1 = ida.id.accept(this);
        Temporary t2 = ida.expr.accept(this);
        String ir;

        boolean isSubtype = t2.type.isSubtypeOf(t1.type) && !t2.type.equals(t1.type);
        if (isSubtype) {
            Temporary t3 = currentTempManager.getAvailableTemporary(t1.type);
            ir = t3 + " := " + t2.getType() + "2" + t1.getType() + " " + t2 + ";";
            currentFunctionEnvironment.IRInstructionList.add(ir);

            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            String temp_asm = t2.getLoadInstruction() + " " + t2.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = t2.getType().toLowerCase() + "2" + t1.getType().toLowerCase();
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = t3.getStoreInstruction() + " " + t3.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

            t2 = t3;
        }

        ir = t1 + " := " + t2 + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // store into correct variable
        String load_asm = t2.getLoadInstruction() + " " + t2.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        load_asm = t1.getStoreInstruction() + " " + t1.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        // clear temporaries
        currentTempManager.PopTemporaryBlock();
    }

    public Temporary visit(Expression e) {
        return null;
    }

    public Temporary visit(FunctionCall fc) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary t = fc.exprList.accept(this);

        currentTempManager.removeFromCurrentTempBlock(t);
        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return t;
    }

    public Temporary visit(ExpressionList el) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        IRFunctionEnvironment fenv = functionEnvironments.get(el.fid.value);
        Temporary t = fenv.returnType.t.contains("void") ? null
                : currentTempManager.getAvailableTemporary(fenv.returnType);

        String ir = fenv.returnType.t.contains("void") ? "" : t + " := ";

        Iterator<Expression> elit = el.expressionList.iterator();
        Iterator<MyType> paramit = fenv.parameters.values().iterator();
        ir += "CALL ";
        String functionSignature = fenv.functionName + "(";
        String functionSignatureASM = fenv.functionName + "("; 

        Temporary exprTemp;
        MyType paramType;
        Temporary buff;
        String buffir;

        ArrayList<String> valuesToLoad = new ArrayList<String>();
        while (elit.hasNext()) {
            Expression e = elit.next();
            paramType = paramit.next();

            exprTemp = e.accept(this);

            boolean isSubtype = exprTemp.type.isSubtypeOf(paramType) && !exprTemp.type.equals(paramType);
            if (isSubtype) {
                buff = currentTempManager.getAvailableTemporary(paramType);
                buffir = buff + " := " + exprTemp.getType() + "2" + TypeConverter.convertType(paramType) + " "
                        + exprTemp + ";";
                currentFunctionEnvironment.IRInstructionList.add(buffir);

                currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
                String temp_asm = exprTemp.getLoadInstruction() + " " + exprTemp.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = exprTemp.getType().toLowerCase() + "2" + TypeConverter.convertType(paramType).toLowerCase();
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = buff.getStoreInstruction() + " " + buff.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
    
                exprTemp = buff;
            }

            functionSignature += exprTemp;
            
            String type = TypeConverter.convertType(exprTemp.type);
            if (exprTemp.type.isArray) {
                type = "[" + type.substring(1);
            }

            functionSignatureASM += type;
            valuesToLoad.add(exprTemp.getLoadInstruction() + " " + exprTemp.id);
        }

        functionSignature += ")";
        functionSignatureASM += ")";
        ir += functionSignature;
        currentFunctionEnvironment.IRInstructionList.add(ir);
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        for (String s : valuesToLoad) {
            currentFunctionEnvironment.ASMInstructionList.add(s);
        }

        functionSignatureASM += TypeConverter.convertType(fenv.returnType).replace("A", "[");
        currentFunctionEnvironment.ASMInstructionList.add("invokestatic " + this.pname + "/" + functionSignatureASM);
        if (t != null){
            currentFunctionEnvironment.ASMInstructionList.add(t.getStoreInstruction() + " " + t.id);
        }

        currentTempManager.removeFromCurrentTempBlock(t);
        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return t;
    }

    public Temporary visit(AddSubtractExpression asExp) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary lhs = asExp.left.accept(this);
        Temporary rhs = asExp.right.accept(this);

        // crazy swap to convert subtype to super type
        boolean isSubtype = (lhs.type.isSubtypeOf(rhs.type) && !lhs.type.equals(rhs.type))
                || (rhs.type.isSubtypeOf(lhs.type) && !rhs.type.equals(lhs.type));
        if (isSubtype) {
            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            MyType convertTo = (lhs.type.isSubtypeOf(rhs.type) && !lhs.type.equals(rhs.type)) ? rhs.type : lhs.type;
            Temporary buff;
            String buffir;

            buff = currentTempManager.getAvailableTemporary(convertTo);

            if (!lhs.type.equals(convertTo)) {
                buffir = buff + " := " + lhs.getType() + "2" + TypeConverter.convertType(convertTo) + " " + lhs + ";";
 
                String temp_asm = lhs.getLoadInstruction() + " " + lhs.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = lhs.getType().toLowerCase() + "2" + TypeConverter.convertType(convertTo).toLowerCase();
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = buff.getStoreInstruction() + " " + buff.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

                lhs = buff;
            } else {
                buffir = buff + " := " + rhs.getType() + "2" + TypeConverter.convertType(convertTo) + " " + rhs + ";";

                String temp_asm = rhs.getLoadInstruction() + " " + rhs.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = rhs.getType().toLowerCase() + "2" + TypeConverter.convertType(convertTo).toLowerCase();
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = buff.getStoreInstruction() + " " + buff.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

                rhs = buff;
            }

            currentFunctionEnvironment.IRInstructionList.add(buffir);
        }

        Temporary t = currentTempManager.getAvailableTemporary(lhs.type);
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        String ir = t + " := " + lhs + " " + lhs.getType() + asExp.operand + " " + rhs + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        if (lhs.getType().equals("U")) {
            currentFunctionEnvironment.ASMInstructionList.add("new java/lang/StringBuffer");
            currentFunctionEnvironment.ASMInstructionList.add("dup");
            currentFunctionEnvironment.ASMInstructionList.add("invokenonvirtual java/lang/StringBuffer/<init>()V");
            currentFunctionEnvironment.ASMInstructionList.add("aload " + lhs.id);
            currentFunctionEnvironment.ASMInstructionList.add("invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;");
            currentFunctionEnvironment.ASMInstructionList.add("aload " + rhs.id);
            currentFunctionEnvironment.ASMInstructionList.add("invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;");
            currentFunctionEnvironment.ASMInstructionList.add("invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;");
            currentFunctionEnvironment.ASMInstructionList.add("astore " + t.id);
        } else {
            currentFunctionEnvironment.ASMInstructionList.add(lhs.getLoadInstruction() + " " + lhs.id);
            currentFunctionEnvironment.ASMInstructionList.add(rhs.getLoadInstruction() + " " + rhs.id);
            String type = lhs.type.t.equals("float") ? "f" : "i";
            String op = asExp.operand.equals("+") ? "add" : "sub";
            currentFunctionEnvironment.ASMInstructionList.add(type + op);
            if (lhs.type.t.equals("char")) {
                currentFunctionEnvironment.ASMInstructionList.add("i2c");
            }
            currentFunctionEnvironment.ASMInstructionList.add(t.getStoreInstruction() + " " + t.id);
        }

        currentTempManager.removeFromCurrentTempBlock(t);
        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return t;
    }

    public Temporary visit(MultiplyExpression multExp) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary lhs = multExp.left.accept(this);
        Temporary rhs = multExp.right.accept(this);

        // crazy swap to convert subtype to super type
        boolean isSubtype = (lhs.type.isSubtypeOf(rhs.type) && !lhs.type.equals(rhs.type))
                || (rhs.type.isSubtypeOf(lhs.type) && !rhs.type.equals(lhs.type));
        if (isSubtype) {
            MyType convertTo = (lhs.type.isSubtypeOf(rhs.type) && !lhs.type.equals(rhs.type)) ? rhs.type : lhs.type;
            String buffir;

            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            Temporary buff = currentTempManager.getAvailableTemporary(convertTo);

            if (!lhs.type.equals(convertTo)) {
                buffir = buff + " := " + lhs.getType() + "2" + TypeConverter.convertType(convertTo) + " " + lhs + ";";

                String temp_asm = lhs.getLoadInstruction() + " " + lhs.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = lhs.getType().toLowerCase() + "2" + TypeConverter.convertType(convertTo).toLowerCase();
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = buff.getStoreInstruction() + " " + buff.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

                lhs = buff;
            } else {
                buffir = buff + " := " + rhs.getType() + "2" + TypeConverter.convertType(convertTo) + " " + rhs + ";";

                String temp_asm = rhs.getLoadInstruction() + " " + rhs.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = rhs.getType().toLowerCase() + "2" + TypeConverter.convertType(convertTo).toLowerCase();
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
                temp_asm = buff.getStoreInstruction() + " " + buff.id;
                currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

                rhs = buff;
            }

            currentFunctionEnvironment.IRInstructionList.add(buffir);
        }

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        Temporary t = currentTempManager.getAvailableTemporary(lhs.type);
        String ir = t + " := " + lhs + " " + lhs.getType() + multExp.operand + " " + rhs + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add(lhs.getLoadInstruction() + " " + lhs.id);
        currentFunctionEnvironment.ASMInstructionList.add(rhs.getLoadInstruction() + " " + rhs.id);
        String type = lhs.type.t.equals("float") ? "f" : "i";
        currentFunctionEnvironment.ASMInstructionList.add(type + "mul");
        currentFunctionEnvironment.ASMInstructionList.add(t.getStoreInstruction() + " " + t.id);

        currentTempManager.removeFromCurrentTempBlock(t);
        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return t;
    }

    public Temporary visit(LessThanExpression lte) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary lhs = lte.left.accept(this);
        Temporary rhs = lte.right.accept(this);

        // type conversion
        boolean isSubtype = rhs.type.isSubtypeOf(lhs.type) && !rhs.type.equals(lhs.type);
        if (isSubtype) {
            Temporary buff = currentTempManager.getAvailableTemporary(lhs.type);
            String buffir = buff + " := " + rhs.getType() + "2" + buff.getType() + " " + rhs + ";";
            currentFunctionEnvironment.IRInstructionList.add(buffir);

            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            String temp_asm = rhs.getLoadInstruction() + " " + rhs.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = rhs.getType().toLowerCase() + "2" + buff.getType().toLowerCase();
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = buff.getStoreInstruction() + " " + buff.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

            rhs = buff;
        }

        MyType ctype = new booleanType("boolean", 0, 0);
        Temporary t = currentTempManager.getAvailableTemporary(ctype);
        String ir = t + " := " + lhs + " " + lhs.getType() + lte.operand + " " + rhs + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(lhs.getLoadInstruction() + " " + lhs.id);
        currentFunctionEnvironment.ASMInstructionList.add(rhs.getLoadInstruction() + " " + rhs.id);
        currentFunctionEnvironment.ASMInstructionList.add(lhs.getLessThanInstruction()); 

        String l1 = getLabelID();
        String l2 = getLabelID();

        currentFunctionEnvironment.ASMInstructionList.add("iflt L_" + l1);
        currentFunctionEnvironment.ASMInstructionList.add("ldc 0");
        currentFunctionEnvironment.ASMInstructionList.add("goto L_" + l2);
        currentFunctionEnvironment.ASMInstructionList.add("L_" + l1 + ":");
        currentFunctionEnvironment.ASMInstructionList.add("ldc 1");
        currentFunctionEnvironment.ASMInstructionList.add("L_" + l2 + ":");
        currentFunctionEnvironment.ASMInstructionList.add(t.getStoreInstruction() + " " + t.id);

        currentTempManager.removeFromCurrentTempBlock(t);
        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return t;
    }

    public Temporary visit(EqualityExpression eqExp) throws IRException {
        // save temporaries
        currentTempManager.PushTemporaryBlock();

        Temporary lhs = eqExp.left.accept(this);
        Temporary rhs = eqExp.right.accept(this);

        // type conversion
        boolean isSubtype = rhs.type.isSubtypeOf(lhs.type) && !rhs.type.equals(lhs.type);
        if (isSubtype) {
            Temporary buff = currentTempManager.getAvailableTemporary(lhs.type);
            String buffir = buff + " := " + rhs.getType() + "2" + buff.getType() + " " + rhs + ";";
            currentFunctionEnvironment.IRInstructionList.add(buffir);

            currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
            String temp_asm = rhs.getLoadInstruction() + " " + rhs.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = rhs.getType().toLowerCase() + "2" + buff.getType().toLowerCase();
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);
            temp_asm = buff.getStoreInstruction() + " " + buff.id;
            currentFunctionEnvironment.ASMInstructionList.add(temp_asm);

            rhs = buff;
        }

        MyType ctype = new booleanType("boolean", 0, 0);
        Temporary t = currentTempManager.getAvailableTemporary(ctype);
        String ir = t + " := " + lhs + " " + lhs.getType() + eqExp.operand + " " + rhs + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");
        currentFunctionEnvironment.ASMInstructionList.add(lhs.getLoadInstruction() + " " + lhs.id);
        currentFunctionEnvironment.ASMInstructionList.add(rhs.getLoadInstruction() + " " + rhs.id);
        currentFunctionEnvironment.ASMInstructionList.add(lhs.getLessThanInstruction());

        String l1 = getLabelID();
        String l2 = getLabelID();

        currentFunctionEnvironment.ASMInstructionList.add("ifeq L_" + l1);
        currentFunctionEnvironment.ASMInstructionList.add("ldc 0");
        currentFunctionEnvironment.ASMInstructionList.add("goto L_" + l2);
        currentFunctionEnvironment.ASMInstructionList.add("L_" + l1 + ":");
        currentFunctionEnvironment.ASMInstructionList.add("ldc 1");
        currentFunctionEnvironment.ASMInstructionList.add("L_" + l2 + ":");
        currentFunctionEnvironment.ASMInstructionList.add(t.getStoreInstruction() + " " + t.id);

        currentTempManager.removeFromCurrentTempBlock(t);
        // clear temporaries
        currentTempManager.PopTemporaryBlock();
        return t;
    }

    public Temporary visit(ParenExpression p) throws IRException {
        return p.expr.accept(this);
    }

    public Temporary visit(ArrayReference a) throws IRException {
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // for each declaration add to environment
        Temporary array = a.id.accept(this);
        Temporary arrayIndex = a.expr.accept(this);
        array.indexTempId = arrayIndex.id;

        // store array value in temp and add to irlist
        MyType arrayItemType = new MyType(array.type.t, 0, 0);
        Temporary intermediary = currentTempManager.getAvailableTemporary(arrayItemType);
        String ir = intermediary + " := " + array + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);

        String load_asm = "aload " + array.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        load_asm = arrayIndex.getLoadInstruction() + " " + arrayIndex.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        currentFunctionEnvironment.ASMInstructionList.add(array.getArrayLoadInstruction());
        load_asm = intermediary.getStoreInstruction() + " " + intermediary.id;
        currentFunctionEnvironment.ASMInstructionList.add(load_asm);

        return intermediary;
    }

    public Temporary visit(Identifier i) throws IRException {
        Temporary t = currentFunctionEnvironment.paramsToTemp.get(i.value);
        if (t == null) {
            t = currentFunctionEnvironment.varsToTemp.get(i.value);
            if (t == null) {
                throw new IRException("Cannot find identifier " + i + "in environment.");
            }
        }

        // load identifier onto stack
        // String load_asm = t.getLoadInstruction() + " " + t.id;
        // currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        return t;
    }

    public Temporary visit(CharLiteral charLit) throws IRException {
        MyType ctype = new charType("char", charLit.lineNumber, charLit.offset);
        Temporary t = currentTempManager.getAvailableTemporary(ctype);

        if (charLit.value.length() <= 2) {
            throw new IRException("Character literals must contain a character.\n");
        }

        String ir = t + " := " + charLit.value + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        // add marker for ir line in asm instruction
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // add ASM instructions
        String load_asm = "ldc " + (int)charLit.value.toCharArray()[0];
        String store_asm = "istore " + t.id;

        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        currentFunctionEnvironment.ASMInstructionList.add(store_asm);
        return t;
    }

    public Temporary visit(BooleanLiteral b) throws IRException {
        MyType ctype = new booleanType("boolean", b.lineNumber, b.offset);
        Temporary t = currentTempManager.getAvailableTemporary(ctype);

        String ir = t + " := " + b.value.toUpperCase() + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        // add marker for ir line in asm instruction
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // add ASM instructions
        String load_asm = b.value.equals("true") ? "ldc 1" : "ldc 0";
        String store_asm = "istore " + t.id;

        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        currentFunctionEnvironment.ASMInstructionList.add(store_asm);
        return t;
    }

    public Temporary visit(FloatLiteral f) throws IRException {
        MyType ctype = new floatType("float", f.lineNumber, f.offset);
        Temporary t = currentTempManager.getAvailableTemporary(ctype);

        String ir = t + " := " + f.value + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        // add marker for ir line in asm instruction
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // add ASM instructions
        String load_asm = "ldc " + f.value;
        String store_asm = "fstore " + t.id;

        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        currentFunctionEnvironment.ASMInstructionList.add(store_asm);
        return t;
    }

    public Temporary visit(IntegerLiteral i) throws IRException {
        MyType ctype = new intType("int", i.lineNumber, i.offset);

        Temporary t = currentTempManager.getAvailableTemporary(ctype);

        String ir = t + " := " + i.value + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        // add marker for ir line in asm instruction
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // add ASM instructions
        String load_asm = "ldc " + i.value;
        String store_asm = "istore " + t.id;

        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        currentFunctionEnvironment.ASMInstructionList.add(store_asm);
        return t;
    }

    public Temporary visit(StringLiteral s) throws IRException {
        MyType ctype = new stringType("string", s.lineNumber, s.offset);
        Temporary t = currentTempManager.getAvailableTemporary(ctype);

        // add IR instructions
        String ir = t + " := " + s.value + ";";
        currentFunctionEnvironment.IRInstructionList.add(ir);
        // add marker for ir line in asm instruction
        currentFunctionEnvironment.ASMInstructionList.add("new_instruction");

        // add ASM instructions
        String load_asm = "ldc " + s.value;
        String store_asm = "astore " + t.id;

        currentFunctionEnvironment.ASMInstructionList.add(load_asm);
        currentFunctionEnvironment.ASMInstructionList.add(store_asm);
        return t;
    }
}
grammar ulGrammar;
options {
  backtrack=true;
}
	
@header {
	import AST.*;
	import MyTypes.*;
	import Semantic.*;
	import IR.*;
}

@members
{
protected void mismatch (IntStream input, int ttype, BitSet follow)
        throws RecognitionException
{
        throw new MismatchedTokenException(ttype, input);
}
public void recoverFromMismatchedSet (IntStream input,
                                      RecognitionException e,
                                      BitSet follow)
throws RecognitionException
{
	reportError(e);
	throw e;
}
}

@rulecatch {
        catch (RecognitionException ex) {
                reportError(ex);
                throw ex;
        }
}

program returns [Program prog]
@init
{
	prog = new Program();
}
: (f=function {prog.addElement(f);})+ EOF
;

function returns [Function f]
: fd=functionDecl fb=functionBody { f = new Function(fd,fb);}
;

functionDecl returns [FunctionDecl fd]
: ct=compoundType i=identifier '(' fp=formalParameters ')' { fd = new FunctionDecl(ct, i, fp);}
;

formalParameters returns [FormalParameter fp]
@init
{
	fp = new FormalParameter();
}
: ct=compoundType i=identifier { FunctionParameter p = new FunctionParameter(ct,i); fp.AddMoreFormals(p); }
(mf=moreFormals {fp.AddMoreFormals(mf);})*
| 
; 

moreFormals returns [FunctionParameter fp]
: ',' ct=compoundType i=identifier {fp = new FunctionParameter(ct, i);}
; 

compoundType returns [MyType type]
:	  t=type { type = t; }
	| t=type '[' s=INTEGERCONSTANT ']' { type = t; type.setArraySize($s.text); type.setIsArray();}
;

type returns [MyType type]
:	  s='int' { type = new intType($s.text, $s.line, $s.pos); }
	| s='float' { type = new floatType($s.text, $s.line, $s.pos); }
	| s='char' { type = new charType($s.text, $s.line, $s.pos); }
	| s='string' { type = new stringType($s.text, $s.line, $s.pos); }
	| s='boolean' { type = new booleanType($s.text, $s.line, $s.pos); }
	| s='void' { type = new voidType($s.text, $s.line, $s.pos); }
	;

functionBody returns [FunctionBody fb]
@init{
	fb = new FunctionBody();
}
:
	'{' (vd=varDecl {fb.AddVarDecl(vd);})* (s=statement {fb.AddStatement(s);})* '}'
;

varDecl returns [VariableDeclaration vd]
: ctype=compoundType id=identifier ';' { vd = new VariableDeclaration(ctype, id); } 
;

statement returns [Statement s]
:
	  v=ifElseStatement { s = v; }
	| v=ifStatement { s = v; }
	| v=printStatement { s = v; }
	| v=printLnStatement { s = v; }
	| v=returnStatement { s = v; }
	| v=whileStatement { s = v; }
	| v=arrayAssignment { s = v; }
	| v=idAssignmentStatement { s = v;}
 	| v=exprStatement { s = v; }
	| ';' { s = new SemiColonStatement(); }
	;

printStatement returns [Statement ps]
: 
PRINT e=expr ';' { ps = new PrintStatement(e); }
;

printLnStatement returns [Statement pls]
:
PRINTLN e=expr ';' { pls = new PrintLnStatement(e); }
;

ifElseStatement returns [Statement ies]
: 
IF '(' e=expr ')' b1=block ELSE b2=block { ies = new IfElseStatement(e,b1,b2); }
;

ifStatement returns [Statement is]
:
IF '(' e=expr ')' b=block { is = new IfStatement(e,b); }
;

returnStatement returns [Statement rs]
: 
o=RETURN (e=expr)? ';' { rs = new ReturnStatement(e, $o.line, $o.pos); }
;

whileStatement returns [Statement wstat]
:
WHILE '(' e=expr ')' b=block { wstat = new WhileStatement(e,b); }
;

idAssignmentStatement returns [Statement isa]
:
i=identifier ASSIGNMENT e=expr ';' { isa = new IdAssignmentStatement(i,e);} 
;

arrayAssignment returns [Statement aa]
: 
i=identifier '[' e1=expr ']' ASSIGNMENT e2=expr ';' { aa = new ArrayAssignmentStatement(i,e1,e2); }
;

exprStatement returns [Statement exprStat]
:
e=expr ';' { exprStat = new ExpressionStatement(e); }
;

block returns [Block b]
@init
{
	b = new Block();
}
:
'{' (s=statement {b.AddStatement(s);})* '}'
;

/* 
Original mutually left recursive expr:
expr:  expr OPERAND expr
	| literal	
	| identifier	
	| identifier '[' expr ']'
	| identifier '(' exprList ')'
	| '(' expr ')';
*/


atom returns [Expression e]
:
	  l=literal { e = l; } 
	| i=identifier { e = i; }
	| a=arrayReference { e = a; }
	| f=functionCall { e = f; }
	| pe=parenExpression { e = pe; }
;

expr returns [Expression e]
: v=equality { e = v; }
;

multiply returns [Expression e]
:
v=atom { e = v;} ( op=MULTIPLY e2=atom { e = new MultiplyExpression(e,e2, $op.line, $op.pos); } )*
;

addSubtract returns [Expression e]
:
v=multiply { e = v;} ( op=PLUSORMINUS e2=multiply { e = new AddSubtractExpression($op.text, e, e2, $op.line, $op.pos); } )*
;

lessthan returns [Expression e]
:
v=addSubtract { e = v;} ( op=LESSTHAN e2=addSubtract { e = new LessThanExpression(v, e2, $op.line, $op.pos); } )*
;

equality returns [Expression e]
:
v=lessthan { e = v;} ( op=EQUALS e2=lessthan { e = new EqualityExpression(v, e2, $op.line, $op.pos); } )*
;


arrayReference returns [ArrayReference ai]
:
i=identifier '[' e=expr ']' { ai = new ArrayReference(i, e); }
;

functionCall returns [FunctionCall f]
:
i=identifier '(' el=exprList ')' { f = new FunctionCall(i, el); el.SetFunctionIdentifier(i); }
;

parenExpression returns [ParenExpression pe]
:
b='(' e1=expr ')' { pe = new ParenExpression(e1, $b.line, $b.pos); } 
;

literal returns [Expression l]
:
	  v=TRUE { l = new BooleanLiteral($v.text, $v.line, $v.pos); }
	| v=FALSE { l = new BooleanLiteral($v.text, $v.line, $v.pos); } 
	| v=INTEGERCONSTANT { l = new IntegerLiteral($v.text, $v.line, $v.pos); }
	| v=FLOATCONSTANT { l = new FloatLiteral($v.text, $v.line, $v.pos); }	
	| v=CHARCONSTANT { l = new CharLiteral($v.text, $v.line, $v.pos); }
	| v=STRINGCONSTANT { l = new StringLiteral($v.text, $v.line, $v.pos); }
	;

exprList returns [ExpressionList el]
@init
{
	el = new ExpressionList();
}
:
(e=expr { el.AddToList(e);} )  (e1=exprMore { el.AddToList(e1); })* | 
;

exprMore returns [Expression e]
: 
',' e1=expr { e = e1; }
;

identifier returns [Identifier id]
:
v=ID {id = new Identifier($v.text, $v.line, $v.pos);}
;

/* Lexer rules */

fragment DOUBLEQUOTE: '\"' ;
fragment SINGLEQUOTE: '\'' ;

fragment LETTER: ('a'..'z'|'A'..'Z') ;
fragment DIGIT: ('0'..'9') ;

LESSTHAN: '<' ;
EQUALS: '==' ;
PLUSORMINUS: ('+' | '-') ;
MULTIPLY:  '*' ;

PRINT: 'print';
PRINTLN: 'println';

IF: 'if' ;
ELSE: 'else' ;
RETURN: 'return' ;
WHILE: 'while' ;
TRUE: 'true' ;
FALSE: 'false' ;

INTEGERCONSTANT: DIGIT+ ;
FLOATCONSTANT: DIGIT+ '.' DIGIT+ ;
CHARCONSTANT: SINGLEQUOTE .? SINGLEQUOTE ;
STRINGCONSTANT: DOUBLEQUOTE .* DOUBLEQUOTE ;

ASSIGNMENT: '=' ;
ID: (LETTER | '_') (LETTER | '_' | DIGIT)* ;

WHITESPACE: ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;} ;
COMMENT: '//' ~('\r' | '\n')* ('\r' | '\n') {$channel = HIDDEN;} ;

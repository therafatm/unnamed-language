lexer grammar ulGrammar;

T28 : '(' ;
T29 : ')' ;
T30 : ',' ;
T31 : '[' ;
T32 : ']' ;
T33 : 'int' ;
T34 : 'float' ;
T35 : 'char' ;
T36 : 'string' ;
T37 : 'boolean' ;
T38 : 'void' ;
T39 : '{' ;
T40 : '}' ;
T41 : ';' ;

// $ANTLR src "ulGrammar.g" 252
fragment DOUBLEQUOTE: '\"' ;
// $ANTLR src "ulGrammar.g" 253
fragment SINGLEQUOTE: '\'' ;

// $ANTLR src "ulGrammar.g" 255
fragment LETTER: ('a'..'z'|'A'..'Z') ;
// $ANTLR src "ulGrammar.g" 256
fragment DIGIT: ('0'..'9') ;

// $ANTLR src "ulGrammar.g" 258
LESSTHAN: '<' ;
// $ANTLR src "ulGrammar.g" 259
EQUALS: '==' ;
// $ANTLR src "ulGrammar.g" 260
PLUSORMINUS: ('+' | '-') ;
// $ANTLR src "ulGrammar.g" 261
MULTIPLY:  '*' ;

// $ANTLR src "ulGrammar.g" 263
PRINT: 'print';
// $ANTLR src "ulGrammar.g" 264
PRINTLN: 'println';

// $ANTLR src "ulGrammar.g" 266
IF: 'if' ;
// $ANTLR src "ulGrammar.g" 267
ELSE: 'else' ;
// $ANTLR src "ulGrammar.g" 268
RETURN: 'return' ;
// $ANTLR src "ulGrammar.g" 269
WHILE: 'while' ;
// $ANTLR src "ulGrammar.g" 270
TRUE: 'true' ;
// $ANTLR src "ulGrammar.g" 271
FALSE: 'false' ;

// $ANTLR src "ulGrammar.g" 273
INTEGERCONSTANT: DIGIT+ ;
// $ANTLR src "ulGrammar.g" 274
FLOATCONSTANT: DIGIT+ '.' DIGIT+ ;
// $ANTLR src "ulGrammar.g" 275
CHARCONSTANT: SINGLEQUOTE .? SINGLEQUOTE ;
// $ANTLR src "ulGrammar.g" 276
STRINGCONSTANT: DOUBLEQUOTE .* DOUBLEQUOTE ;

// $ANTLR src "ulGrammar.g" 278
ASSIGNMENT: '=' ;
// $ANTLR src "ulGrammar.g" 279
ID: (LETTER | '_') (LETTER | '_' | DIGIT)* ;

// $ANTLR src "ulGrammar.g" 281
WHITESPACE: ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;} ;
// $ANTLR src "ulGrammar.g" 282
COMMENT: '//' ~('\r' | '\n')* ('\r' | '\n') {$channel = HIDDEN;} ;

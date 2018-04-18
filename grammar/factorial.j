.source factorial.ir
.class public factorial
.super java/lang/Object

.method public static factorial(I)I
	.limit locals 7
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  Z from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0
	istore 4
	ldc 0
	istore 5
	ldc 0
	istore 6

.line 12
;				T2 := 1;
	ldc 1
	istore 2
.line 13
;				T3 := T0 I== T2;
	iload 0
	iload 2
	isub
	ifeq L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 3
.line 14
;				T3 := Z! T3;
	iload 3
	ldc 1
	ixor
	istore 3
.line 15
;				IF T3 GOTO L0;
	iload 3
	ifne L0
.line 16
;				T2 := 1;
	ldc 1
	istore 2
.line 17
;				T1 := T2;
	iload 2
	istore 1
.line 18
;				GOTO L1;
	goto L1
.line 19
;				L0:;
L0:
.line 20
;				T5 := 1;
	ldc 1
	istore 5
.line 21
;				T6 := T0 I- T5;
	iload 0
	iload 5
	isub
	istore 6
.line 22
;				T4 := CALL factorial(T6)
	iload 6
	invokestatic factorial/factorial(I)I
	istore 4
.line 23
;				T5 := T0 I* T4;
	iload 0
	iload 4
	imul
	istore 5
.line 24
;				T1 := T5;
	iload 5
	istore 1
.line 25
;				GOTO L1;
	goto L1
.line 26
;				L1:;
L1:
.line 27
;				RETURN T1;
	iload 1
	ireturn
.line 28
;				RETURN;
	return
L_1:
.end method

.method public static __main()V
	.limit locals 3
	.var 0 is T0  Ljava/lang/String; from L_4 to L_5
	.var 1 is T1  I from L_4 to L_5
	.var 2 is T2  I from L_4 to L_5
	.limit stack 35
L_4:
	aconst_null
	astore 0
	ldc 0
	istore 1
	ldc 0
	istore 2

.line 37
;				T0 := "The factorial of 9 is ";
	ldc "The factorial of 9 is "
	astore 0
.line 38
;				PRINTU T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 0
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 39
;				T2 := 9;
	ldc 9
	istore 2
.line 40
;				T1 := CALL factorial(T2)
	iload 2
	invokestatic factorial/factorial(I)I
	istore 1
.line 41
;				PRINTLNI T1;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 1
	invokevirtual java/io/PrintStream/println(I)V
.line 42
;				RETURN;
	return
L_5:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic factorial/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

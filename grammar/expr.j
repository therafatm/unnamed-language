.source expr.ir
.class public expr
.super java/lang/Object

.method public static __main()V
	.limit locals 8
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.var 7 is T7  I from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 0
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
	ldc 0
	istore 7

.line 13
;				T3 := 1;
	ldc 1
	istore 3
.line 14
;				T0 := T3;
	iload 3
	istore 0
.line 15
;				T3 := 2;
	ldc 2
	istore 3
.line 16
;				T1 := T3;
	iload 3
	istore 1
.line 17
;				T4 := 3;
	ldc 3
	istore 4
.line 18
;				T2 := T4;
	iload 4
	istore 2
.line 19
;				T4 := 1;
	ldc 1
	istore 4
.line 20
;				T5 := T1 I+ T4;
	iload 1
	iload 4
	iadd
	istore 5
.line 21
;				T6 := T0 I* T5;
	iload 0
	iload 5
	imul
	istore 6
.line 22
;				T5 := 7;
	ldc 7
	istore 5
.line 23
;				T7 := T6 I+ T5;
	iload 6
	iload 5
	iadd
	istore 7
.line 24
;				T2 := T7;
	iload 7
	istore 2
.line 25
;				PRINTLNI T2;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 2
	invokevirtual java/io/PrintStream/println(I)V
.line 26
;				RETURN;
	return
L_1:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic expr/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

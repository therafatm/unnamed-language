.source ar.ir
.class public ar
.super java/lang/Object

.method public static __main()V
	.limit locals 7
	.var 0 is T0  [I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.limit stack 35
L_0:
	aconst_null
	astore 0
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
;				T0 := NEWARRAY I 3;
	ldc 3
	newarray int
	astore 0
.line 13
;				T2 := 0;
	ldc 0
	istore 2
.line 14
;				T3 := 7;
	ldc 7
	istore 3
.line 15
;				T0[T2] := T3;
	aload 0
	iload 2
	iload 3
	iastore
.line 16
;				T2 := 1;
	ldc 1
	istore 2
.line 17
;				T1 := T2;
	iload 2
	istore 1
.line 18
;				T3 := 1;
	ldc 1
	istore 3
.line 19
;				T4 := T1 I+ T3;
	iload 1
	iload 3
	iadd
	istore 4
.line 20
;				T5 := 2;
	ldc 2
	istore 5
.line 21
;				T0[T4] := T5;
	aload 0
	iload 4
	iload 5
	iastore
.line 22
;				T4 := 1;
.line 23
;				T5 := T1 I+ T4;
	ldc 1
	istore 4
.line 24
;				T6 := T0[T5];
	iload 1
	iload 4
	iadd
	istore 5
	aload 0
	iload 5
	iaload
	istore 6
.line 25
;				PRINTLNI T6;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 6
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
	invokestatic ar/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

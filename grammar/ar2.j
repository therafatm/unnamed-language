.source ar2.ir
.class public ar2
.super java/lang/Object

.method public static __main()V
	.limit locals 9
	.var 0 is T0  [I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  Z from L_0 to L_1
	.var 7 is T7  I from L_0 to L_1
	.var 8 is T8  I from L_0 to L_1
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
	ldc 0
	istore 7
	ldc 0
	istore 8

.line 14
;				T0 := NEWARRAY I 3;
	ldc 3
	newarray int
	astore 0
.line 15
;				T2 := 0;
	ldc 0
	istore 2
.line 16
;				T3 := 1;
	ldc 1
	istore 3
.line 17
;				T0[T2] := T3;
	aload 0
	iload 2
	iload 3
	iastore
.line 18
;				T2 := 1;
	ldc 1
	istore 2
.line 19
;				T3 := 2;
	ldc 2
	istore 3
.line 20
;				T0[T2] := T3;
	aload 0
	iload 2
	iload 3
	iastore
.line 21
;				T4 := 2;
	ldc 2
	istore 4
.line 22
;				T5 := 3;
	ldc 3
	istore 5
.line 23
;				T0[T4] := T5;
	aload 0
	iload 4
	iload 5
	iastore
.line 24
;				T4 := 0;
	ldc 0
	istore 4
.line 25
;				T1 := T4;
	iload 4
	istore 1
.line 26
;				L0:;
L0:
.line 27
;				T5 := 3;
	ldc 3
	istore 5
.line 28
;				T6 := T1 I< T5;
	iload 1
	iload 5
	isub
	iflt L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 6
.line 29
;				T6 := Z! T6;
	iload 6
	ldc 1
	ixor
	istore 6
.line 30
;				IF T6 GOTO L1;
	iload 6
	ifne L1
.line 31
;				T7 := T0[T1];
	aload 0
	iload 1
	iaload
	istore 7
.line 32
;				PRINTLNI T7;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 7
	invokevirtual java/io/PrintStream/println(I)V
.line 33
;				T7 := 1;
	ldc 1
	istore 7
.line 34
;				T8 := T1 I+ T7;
	iload 1
	iload 7
	iadd
	istore 8
.line 35
;				T1 := T8;
	iload 8
	istore 1
.line 36
;				GOTO L0;
	goto L0
.line 37
;				L1:;
L1:
.line 38
;				RETURN;
	return
L_1:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic ar2/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
